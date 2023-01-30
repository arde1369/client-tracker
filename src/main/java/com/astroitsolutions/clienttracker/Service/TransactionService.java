package com.astroitsolutions.clienttracker.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.astroitsolutions.clienttracker.Entity.Transaction;
import com.astroitsolutions.clienttracker.Repository.TransactionRepository;

import io.micrometer.common.util.StringUtils;

@Service
public class TransactionService {

    public static final String DATE_FORMAT = "yyyy-MM-dd";

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> findAllTransactionsByCreatedTimeStamp(String from, String to) throws ParseException{

        Date toDate = null;
        Date fromDate = new SimpleDateFormat(DATE_FORMAT).parse(from);
        if(StringUtils.isEmpty(to)){
            DateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
            Date today = new Date();
            toDate = formatter.parse(formatter.format(today));
        } else {
            toDate = new SimpleDateFormat(DATE_FORMAT).parse(to);
        }

        return transactionRepository.findByCreatedTimeStampBetween(fromDate, toDate);
    }
}
