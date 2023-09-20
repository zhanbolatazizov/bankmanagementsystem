package com.azizov.transaction_service.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    private double balance;
    private double productLimit;
    private double serviceLimit;
    private double monthlySpent;
    private Category category;
    private LocalDate limitSetDate;
    private String currency;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private User user;

}
