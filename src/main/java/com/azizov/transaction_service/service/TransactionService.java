package com.azizov.transaction_service.service;

import com.azizov.transaction_service.model.Account;

import com.azizov.transaction_service.model.Category;
import com.azizov.transaction_service.model.Transaction;
import com.azizov.transaction_service.repository.AccountRepository;
import com.azizov.transaction_service.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    public ResponseEntity<?> convert(double amount, String currencyShortname, double limit) {
        RestTemplate restTemplate = new RestTemplate();

        String url = "https://api.twelvedata.com/currency_conversion?symbol=" + currencyShortname + "/"
                + limit + "&amount=" + amount + "&apikey=82cdce29bc3b440eae41708f428c66ab";

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        return response;
    }

    
    public void createTransaction(String fromAccountNumber, String toAccountNumber, double amount, String category) {
        Account fromAccount = accountRepository.findByAccountNumber(fromAccountNumber);
        Account toAccount = accountRepository.findByAccountNumber(toAccountNumber);

        if (fromAccount == null || toAccount == null) {
            throw new IllegalArgumentException("Счет не найден.");
        }

        double limit = category.equals("PRODUCT") ? fromAccount.getProductLimit() : fromAccount.getServiceLimit();

        if (fromAccount.getMonthlySpent() + amount > limit) {
            Transaction transaction = new Transaction();
            transaction.setFromAccountNumber(fromAccountNumber);
            transaction.setToAccountNumber(toAccountNumber);
            transaction.setAmount(amount);
            transaction.setCategory(Category.valueOf(category));
            transaction.setCreateDate(LocalDateTime.now());
            transaction.setLimitExceeded(true);

            transactionRepository.save(transaction);
        }

        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);
        fromAccount.setMonthlySpent(fromAccount.getMonthlySpent() + amount);

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
    }

    public List<Object[]> findTransactionsExceedingLimitWithInfo() {
        return transactionRepository.findTransactionsExceedingLimitWithInfo();
    }


}
