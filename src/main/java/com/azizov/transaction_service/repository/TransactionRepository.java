package com.azizov.transaction_service.repository;

import com.azizov.transaction_service.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT t, a.limitSetDate, a.productLimit, a.currency FROM Transaction t JOIN Account a ON t.fromAccountNumber = a.number WHERE t.amount > a.productLimit AND t.createDate >= a.limitSetDate")
    List<Object[]> findTransactionsExceedingLimitWithInfo();
}
