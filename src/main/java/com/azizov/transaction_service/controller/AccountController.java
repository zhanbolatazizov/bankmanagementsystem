package com.azizov.transaction_service.controller;

import com.azizov.transaction_service.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/set-monthly-limit")
    public ResponseEntity<?> setMonthlyLimit(
            @RequestParam("accountNumber") String accountNumber,
            @RequestParam("newLimit") double newLimit) {

        accountService.setMonthlyLimit(accountNumber, newLimit);

        return ResponseEntity.ok("Месячный лимит успешно установлен");
    }
}
