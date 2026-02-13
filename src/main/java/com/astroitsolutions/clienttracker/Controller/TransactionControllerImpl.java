package com.astroitsolutions.clienttracker.Controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.astroitsolutions.clienttracker.Service.TransactionService;
import com.astroitsolutions.data_services.Entity.Transaction;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/clienttracker/tansaction")
@Slf4j
@ControllerAdvice
public class TransactionControllerImpl implements TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Override
    @GetMapping("/find")
    public ResponseEntity<List<Transaction>> findAllTransactionsByCreatedTimeStamp(@RequestParam @NonNull String from, @RequestParam String to, @RequestParam int pageSize, @RequestParam int pageNumber) throws ParseException {
        List<Transaction> listOfTransactions = null;
        
        listOfTransactions = transactionService.findAllTransactionsByCreatedTimeStamp(from, to, pageSize, pageNumber);

        return ResponseEntity.ok(listOfTransactions);
    }
}
