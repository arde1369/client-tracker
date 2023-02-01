package com.astroitsolutions.clienttracker.Controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.astroitsolutions.clienttracker.Entity.Transaction;
import com.astroitsolutions.clienttracker.Service.TransactionService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/tansaction")
@Slf4j
public class TransactionControllerImpl implements TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Override
    @GetMapping("/{start}/{end}")
    public ResponseEntity<List<Transaction>> findAllTransactionsByCreatedTimeStamp(@PathVariable @NonNull String from, @PathVariable String to) throws ParseException {
        List<Transaction> listOfTransactions = null;
        try{
            listOfTransactions = transactionService.findAllTransactionsByCreatedTimeStamp(from, to);
        } catch(Exception ex){
            log.error("Unexpected error occurred - ", ex);
                return ResponseEntity
                .badRequest()
                .header("error-message", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .body(null);
        }
        return ResponseEntity.ok(listOfTransactions);
    }
}
