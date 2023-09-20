package com.azizov.transaction_service.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double amount;
    @Column(name = "from_account_number")
    private String fromAccountNumber;
    @Column(name = "to_account_number")
    private String toAccountNumber;
    private Category category;
    @Column(name = "limit_exceeded")
    private boolean limitExceeded;
    private LocalDateTime createDate;
    private String currency;
}
