package com.astroitsolutions.clienttracker.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import java.text.ParseException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.astroitsolutions.clienttracker.Entity.Transaction;
import com.astroitsolutions.clienttracker.Repository.TransactionRepository;
import com.astroitsolutions.clienttracker.Utils.TestUtils;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @Autowired
    @InjectMocks
    private TransactionService transactionService;

    @Mock
    private TransactionRepository transactionRepository;

    TestUtils testUtils = new TestUtils();
    
    @Test
    public void findAllTransactionsBetween_success() throws ParseException{
        List<Transaction> listOfTransactions_mock = testUtils.createTransactionList(testUtils.createNewCompleteClient());

        Mockito.when(transactionRepository.findByCreatedTimeStampBetween(any(), any())).thenReturn(listOfTransactions_mock);

        List<Transaction> listOfTransactions_retreived = transactionService.findAllTransactionsByCreatedTimeStamp("2022-12-01", "2022-12-30");

        assertNotNull(listOfTransactions_retreived);
        assertEquals(2, listOfTransactions_retreived.size());
    }

    @Test
    public void findAllTransactionsBetween_null() throws ParseException{
        Mockito.when(transactionRepository.findByCreatedTimeStampBetween(any(), any())).thenReturn(null);

        List<Transaction> listOfTransactions_retreived = transactionService.findAllTransactionsByCreatedTimeStamp("2022-12-01", "2022-12-30");

        assertNull(listOfTransactions_retreived);
    }
}
