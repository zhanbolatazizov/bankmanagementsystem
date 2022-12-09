package com.example.bankmanagementsystem.services;

import com.example.bankmanagementsystem.models.BankAccount;
import com.example.bankmanagementsystem.models.Transaction;
import com.example.bankmanagementsystem.repositories.TransactionRepo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepo transactionRepo;

    @Autowired
    public TransactionService(TransactionRepo transactionRepo) {
        this.transactionRepo = transactionRepo;
    }

    public Transaction createTransaction(Transaction transaction) {
        BankAccount bankAccount = new BankAccount();
        BankAccount bankAccountDetails = new BankAccount();
        Integer newLimit = bankAccount.getLimit() - transaction.getSum();
        if(newLimit > 0){
            bankAccountDetails.setLimit(newLimit);
            transaction.setLimitExceeded(Boolean.FALSE);
            transaction.setDateTime(new Date());
        } else {
            bankAccountDetails.setLimit(newLimit);
            transaction.setLimitExceeded(Boolean.TRUE);
            transaction.setDateTime(new Date());
        }
        return transactionRepo.save(transaction);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepo.findAll();
    }

    public Transaction getTransactionById(Long id) {
        return  transactionRepo.findById(id).get();
    }

//    public Transaction getLimitExceededTransactions(Transaction transaction) {
//
//        if (transaction.getLimitExceeded().equals(Boolean.TRUE)) {
//            transactionRepo.findAll();
//        }
//        return transaction;
//    }
}
