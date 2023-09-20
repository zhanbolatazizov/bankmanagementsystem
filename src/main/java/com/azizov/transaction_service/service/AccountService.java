package com.azizov.transaction_service.service;

import com.azizov.transaction_service.model.Account;
import com.azizov.transaction_service.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void setMonthlyLimit(String accountNumber, double newLimit) {

        Account account = accountRepository.findByAccountNumber(accountNumber);

        if (account == null) {
            throw new IllegalArgumentException("Счет не найден.");
        }

        if (account.getLimitSetDate() != null) {
            throw new IllegalArgumentException("Существующий лимит уже установлен и не может быть обновлен.");
        }

        if (newLimit <= 0) {
            throw new IllegalArgumentException("Новый лимит должен быть положительным числом.");
        }

        account.setProductLimit(newLimit);
        account.setServiceLimit(newLimit);
        account.setLimitSetDate(LocalDate.now());

        accountRepository.save(account);
    }
}
