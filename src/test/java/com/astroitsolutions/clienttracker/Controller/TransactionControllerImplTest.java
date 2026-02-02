package com.astroitsolutions.clienttracker.Controller;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.test.web.servlet.MockMvc;

import com.astroitsolutions.clienttracker.Entity.Transaction;
import com.astroitsolutions.clienttracker.Entity.Client;
import com.astroitsolutions.clienttracker.Service.TransactionService;
import com.astroitsolutions.clienttracker.Utils.TestUtils;

@SpringBootTest
@AutoConfigureMockMvc
public class TransactionControllerImplTest {
    

    @Autowired
    private TransactionControllerImpl transactionControllerImpl;

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private TransactionService transactionService;

    private TestUtils testUtils = new TestUtils();

    @Test
    public void findAllTransactionsByCreatedTimeStamp_success_200(){

        List<Transaction> listOfTransactions = new ArrayList<>();
        Client mockClient = testUtils.createNewCompleteClient();

        listOfTransactions.add(testUtils.createSingleTransaction(mockClient));

        try {
            when(transactionService.findAllTransactionsByCreatedTimeStamp(anyString(), anyString(), anyInt(), anyInt())).thenReturn(listOfTransactions);

            ResponseEntity<List<Transaction>> responseEntity = transactionControllerImpl.findAllTransactionsByCreatedTimeStamp("12-1-2021", "12-1-2022", 20 ,0);

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
    public void findAllTransactionsByCreatedTimeStamp_parseException_handledByAdvice_400() throws Exception{

        when(transactionService.findAllTransactionsByCreatedTimeStamp(anyString(), anyString(), anyInt(), anyInt())).thenThrow(new ParseException("err", 0));

        mockMvc.perform(get("/api/tansaction/")
                .param("from", "12-1-2021")
                .param("to", "12-1-2022")
                .param("pageSize", "20")
                .param("pageNumber", "0"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("An error occurred when parsing date"));
    }
}
