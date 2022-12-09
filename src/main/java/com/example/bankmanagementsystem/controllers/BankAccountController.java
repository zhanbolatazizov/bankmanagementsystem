package com.example.bankmanagementsystem.controllers;

import com.example.bankmanagementsystem.exceptions.ResourceNotFoundException;
import com.example.bankmanagementsystem.models.BankAccount;
import com.example.bankmanagementsystem.services.BankAccountService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/bankaccount")
@Api(tags = "bankacccounts")
public class BankAccountController {

    private final BankAccountService bankAccountService;

    @Autowired
    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @PostMapping("/save")
    public ResponseEntity<BankAccount> createBankAccount(@RequestBody BankAccount bankAccount) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/bankaccount/save").toUriString());
        return ResponseEntity.ok().body(bankAccountService.createBankAccount(bankAccount));
    }

    @GetMapping
    public ResponseEntity<List<BankAccount>> getAllBankAccounts() {
        return ResponseEntity.ok().body(bankAccountService.getAllBankAccounts());
    }

    @GetMapping("{id}")
    public BankAccount getBankAccountById(@PathVariable Long id)  throws ResourceNotFoundException {
        return bankAccountService.getById(id);
    }

}
