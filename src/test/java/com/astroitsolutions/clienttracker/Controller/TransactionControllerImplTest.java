package com.astroitsolutions.clienttracker.Controller;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import com.astroitsolutions.clienttracker.Entity.Transaction;
import com.astroitsolutions.clienttracker.Entity.Client;
import com.astroitsolutions.clienttracker.Service.TransactionService;
import com.astroitsolutions.clienttracker.Utils.TestUtils;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TransactionControllerImplTest {
    
    @Autowired
    @InjectMocks
    private TransactionControllerImpl transactionControllerImpl;

    @Mock
    private TransactionService transactionService;

    private TestUtils testUtils = new TestUtils();

    @Test
    public void findAllTransactionsByCreatedTimeStamp_success_200(){

        List<Transaction> listOfTransactions = new ArrayList<>();
        Client mockClient = testUtils.createNewCompleteClient();

        listOfTransactions.add(testUtils.createSingleTransaction(mockClient));

        try {
            when(transactionService.findAllTransactionsByCreatedTimeStamp(anyString(), anyString())).thenReturn(listOfTransactions);

            ResponseEntity<List<Transaction>> responseEntity = transactionControllerImpl.findAllTransactionsByCreatedTimeStamp("12-1-2021", "12-1-2022");

            assertNotNull(responseEntity);
            assertNotNull(responseEntity.getBody());
            assertEquals(HttpStatusCode.valueOf(200), responseEntity.getStatusCode());
            assertEquals(listOfTransactions, responseEntity.getBody());
        } catch (ParseException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void findAllTransactionsByCreatedTimeStamp_failure_500(){

        List<Transaction> listOfTransactions = new ArrayList<>();
        Client mockClient = testUtils.createNewCompleteClient();

        listOfTransactions.add(testUtils.createSingleTransaction(mockClient));

        try {
            when(transactionService.findAllTransactionsByCreatedTimeStamp(anyString(), anyString())).thenThrow(new ParseException(null, 0));

            ResponseEntity<List<Transaction>> responseEntity = transactionControllerImpl.findAllTransactionsByCreatedTimeStamp("12-1-2021", "12-1-2022");

            assertNotNull(responseEntity);
            assertNull(responseEntity.getBody());
            assertEquals(HttpStatusCode.valueOf(500), responseEntity.getStatusCode());
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), responseEntity.getHeaders().get("error-message").get(0));
        } catch (ParseException e) {
            e.printStackTrace();
            fail();
        }
    }
}
