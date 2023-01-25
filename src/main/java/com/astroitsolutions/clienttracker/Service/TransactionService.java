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

    private final String DATE_FORMAT = "yyyy-MM-dd";

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> findAllTransactionsByCreatedTimeStamp(String start, String end) throws ParseException{

        Date startDate = null;
        Date endDate = new SimpleDateFormat(DATE_FORMAT).parse(end);
        if(StringUtils.isEmpty(start)){
            DateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
            Date today = new Date();
            startDate = formatter.parse(formatter.format(today));
        } else {
            startDate = new SimpleDateFormat(DATE_FORMAT).parse(start);
        }

        return transactionRepository.findAllByCreatedTimeStampBetween(startDate, endDate);
    }
}
