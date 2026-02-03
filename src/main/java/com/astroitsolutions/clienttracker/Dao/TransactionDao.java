package com.astroitsolutions.clienttracker.Dao;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import com.astroitsolutions.clienttracker.Entity.Transaction;
import com.astroitsolutions.clienttracker.Repository.TransactionRepository;

@Component
public class TransactionDao {
    private TransactionRepository transactionRepository;

    @CircuitBreaker(name = "databaseCircuitBreaker")
    public List<Transaction> findByCreatedTimeStampBetween(Date from, Date to, Pageable pageable){
        return transactionRepository.findByCreatedTimeStampBetween(from, to, pageable);
    }

    @CircuitBreaker(name = "databaseCircuitBreaker")
    public Optional<List<Transaction>> findAllByClientId(int clientId, Pageable pageable){
        return transactionRepository.findAllByClientId(clientId, pageable);
    }
    
}
