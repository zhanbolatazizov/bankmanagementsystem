package com.example.bankmanagementsystem.repositories;

import com.example.bankmanagementsystem.models.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepo extends JpaRepository<BankAccount, Long> {
}
