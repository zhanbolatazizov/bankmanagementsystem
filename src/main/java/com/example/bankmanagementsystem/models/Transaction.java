package com.example.bankmanagementsystem.models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.Date;


@Data
@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "account_from")
    private String accountFrom;

    @Column(name = "account_to")
    private String accountTo;

    @Column(name = "sum")
    private Integer sum;

    @Column(name = "limit_exceeded")
    private Boolean limitExceeded;

    private String category;

    @Column(name = "date_time")
    private Date dateTime;

    @Column(name = "currency_shortname")
    private String currencyShortname;

    public Transaction(String accountFrom, String accountTo, Integer sum, Boolean limitExceeded, String category, Date dateTime) {
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.sum = sum;
        this.limitExceeded = limitExceeded;
        this.category = category;
        this.dateTime = dateTime;
    }

    public Transaction() {
    }
}
