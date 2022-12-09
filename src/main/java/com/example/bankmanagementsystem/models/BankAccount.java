package com.example.bankmanagementsystem.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "bankAccount")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "account")
    private String account;
    @Column(name = "currency_shortname")
    private String currencyShortname;
    @Column(name = "limitt")
    private Integer limit;

    public BankAccount(String account, String currencyShortname, Integer limit) {
        this.account = account;
        this.currencyShortname = currencyShortname;
        this.limit = limit;
    }

    public BankAccount() {
    }
}
