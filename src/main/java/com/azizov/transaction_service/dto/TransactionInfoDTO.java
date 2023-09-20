package com.azizov.transaction_service.dto;

import com.azizov.transaction_service.model.Transaction;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class TransactionInfoDTO {

    private Transaction transaction;
    private LocalDateTime createDate;;
    private double productLimit;
    private String currency;
}
