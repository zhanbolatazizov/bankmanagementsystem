package com.example.bankmanagementsystem.services;

import com.example.bankmanagementsystem.exceptions.ResourceNotFoundException;
import com.example.bankmanagementsystem.models.BankAccount;
import com.example.bankmanagementsystem.repositories.BankAccountRepo;
import com.example.bankmanagementsystem.repositories.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankAccountService {

    private final BankAccountRepo bankAccountRepo;

    @Autowired
    public BankAccountService(BankAccountRepo bankAccountRepo) {
        this.bankAccountRepo = bankAccountRepo;
    }

    public BankAccount createBankAccount(BankAccount bankAccount) {
        return bankAccountRepo.save(bankAccount);
    }

    public List<BankAccount> getAllBankAccounts() {
        return bankAccountRepo.findAll();
    }

    public BankAccount getById(Long id){
        return bankAccountRepo.findById(id).get();
    }


}
