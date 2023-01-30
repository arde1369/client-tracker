package com.astroitsolutions.clienttracker.Controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.astroitsolutions.clienttracker.Entity.Transaction;

@RestController
@RequestMapping("/api/tansaction")
public class TransactionControllerImpl implements TransactionController {

    @Override
    @GetMapping("/{start}/{end}")
    public List<Transaction> findAllTransactionsByCreatedTimeStamp(@PathVariable @NonNull String from, @PathVariable String to) throws ParseException {
        // TODO Auto-generated method stub
        return null;
    }
}
