package com.astroitsolutions.clienttracker.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

import java.text.ParseException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.astroitsolutions.clienttracker.Dao.TransactionDao;
import com.astroitsolutions.clienttracker.Entity.Transaction;
import com.astroitsolutions.clienttracker.Utils.TestUtils;


@SpringBootTest
public class TransactionServiceTest {


    @Autowired
    @InjectMocks
    private TransactionService transactionService;


    @MockBean
    private TransactionDao transactionRepository;

    TestUtils testUtils = new TestUtils();
    
    @Test
    public void findAllTransactionsBetween_success() throws ParseException{
        List<Transaction> listOfTransactions_mock = testUtils.createTransactionList(testUtils.createNewCompleteClient());

        Mockito.when(transactionRepository.findByCreatedTimeStampBetween(any(), any(), Mockito.any(Pageable.class))).thenReturn(listOfTransactions_mock);

        List<Transaction> listOfTransactions_retreived = transactionService.findAllTransactionsByCreatedTimeStamp("2022-12-01", "2022-12-30", 20, 0);

        assertNotNull(listOfTransactions_retreived);
        assertEquals(2, listOfTransactions_retreived.size());
    }

    @Test
    public void findAllTransactionsBetween_nullToDate_success() throws ParseException{
        List<Transaction> listOfTransactions_mock = testUtils.createTransactionList(testUtils.createNewCompleteClient());

        Mockito.when(transactionRepository.findByCreatedTimeStampBetween(any(), any(), Mockito.any(Pageable.class))).thenReturn(listOfTransactions_mock);

        List<Transaction> listOfTransactions_retreived = transactionService.findAllTransactionsByCreatedTimeStamp("2022-12-01", null, 20, 0);

        assertNotNull(listOfTransactions_retreived);
        assertEquals(2, listOfTransactions_retreived.size());
    }

    @Test
    public void findAllTransactionsBetween_emptyToDate_success() throws ParseException{
        List<Transaction> listOfTransactions_mock = testUtils.createTransactionList(testUtils.createNewCompleteClient());

        Mockito.when(transactionRepository.findByCreatedTimeStampBetween(any(), any(), Mockito.any(Pageable.class))).thenReturn(listOfTransactions_mock);

        List<Transaction> listOfTransactions_retreived = transactionService.findAllTransactionsByCreatedTimeStamp("2022-12-01", null, 20 , 0);

        assertNotNull(listOfTransactions_retreived);
        assertEquals(2, listOfTransactions_retreived.size());
    }

    @Test
    public void findAllTransactionsBetween_null() throws ParseException{
        Mockito.when(transactionRepository.findByCreatedTimeStampBetween(any(), any(), Mockito.any(Pageable.class))).thenReturn(null);

        List<Transaction> listOfTransactions_retreived = transactionService.findAllTransactionsByCreatedTimeStamp("2022-12-01", "2022-12-30", 20, 0);

        assertNull(listOfTransactions_retreived);
    }
}
