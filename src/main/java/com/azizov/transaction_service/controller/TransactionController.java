package com.azizov.transaction_service.controller;

import com.azizov.transaction_service.dto.TransactionInfoDTO;
import com.azizov.transaction_service.model.Transaction;
import com.azizov.transaction_service.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/convert")
    public ResponseEntity<?> convertCurrency(
            @RequestParam double amount,
            @RequestParam String currencyShortname,
            @RequestParam double limit) {

        ResponseEntity<?> response = transactionService.convert(amount, currencyShortname, limit);

        return response;
    }

    @PostMapping("/create-transaction")
    public ResponseEntity<?> createTransaction(
            @RequestParam String fromAccountNumber,
            @RequestParam String toAccountNumber,
            @RequestParam double amount,
            @RequestParam String category) {

        transactionService.createTransaction(fromAccountNumber, toAccountNumber, amount, category);

        return ResponseEntity.ok("Транзакция успешно создана");
    }


    @GetMapping("/exceeded-limit-transactions")
    public ResponseEntity<List<TransactionInfoDTO>> getExceededLimitTransactions() {
        List<Object[]> exceededLimitTransactions = transactionService.findTransactionsExceedingLimitWithInfo();

        // Преобразовать результат в DTO (Data Transfer Object)
        List<TransactionInfoDTO> transactionInfoList = new ArrayList<>();
        for (Object[] result : exceededLimitTransactions) {
            Transaction transaction = (Transaction) result[0];
            LocalDateTime limitSetDate = (LocalDateTime) result[1];
            double monthlyProductLimit = (double) result[2];
            String currency = (String) result[3];

            TransactionInfoDTO transactionInfo = new TransactionInfoDTO();
            transactionInfo.setTransaction(transaction);
            transactionInfo.setCreateDate(limitSetDate);
            transactionInfo.setProductLimit(monthlyProductLimit);
            transactionInfo.setCurrency(currency);

            transactionInfoList.add(transactionInfo);
        }

        return ResponseEntity.ok(transactionInfoList);
    }


}
