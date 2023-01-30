package com.astroitsolutions.clienttracker.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.astroitsolutions.clienttracker.Entity.Transaction;

import jakarta.transaction.Transactional;

@Transactional
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findByCreatedTimeStampBetween(Date from, Date to);
}
