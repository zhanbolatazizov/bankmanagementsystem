package com.example.bankmanagementsystem.controllers;

import com.example.bankmanagementsystem.exceptions.ResourceNotFoundException;
import com.example.bankmanagementsystem.models.Transaction;
import com.example.bankmanagementsystem.services.TransactionService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/transaction")
@Api(tags = "transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/save")
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/transaction/save").toUriString());
        return ResponseEntity.ok().body(transactionService.createTransaction(transaction));
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions(){
        return ResponseEntity.ok().body(transactionService.getAllTransactions());
    }

    @GetMapping("{id}")
    public Transaction getTransactionById(@PathVariable Long id) throws ResourceNotFoundException {
        return transactionService.getTransactionById(id);
    }

//    @GetMapping("/limitexceeded")
//    public ResponseEntity<Transaction> getLimitExceededTransactions(@RequestBody Transaction transaction){
//        return ResponseEntity.ok().body(transactionService.getLimitExceededTransactions(transaction));
//    }
}
