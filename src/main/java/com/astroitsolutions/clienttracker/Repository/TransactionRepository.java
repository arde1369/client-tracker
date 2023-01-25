package com.astroitsolutions.clienttracker.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.astroitsolutions.clienttracker.Entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findAllByCreatedTimeStampBetween(Date start, Date end);
}
