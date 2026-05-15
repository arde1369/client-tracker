package com.astroitsolutions.clienttracker.Controller;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatusCode;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.astroitsolutions.data_services.Entity.Transaction;
import com.astroitsolutions.data_services.Entity.Client;
import com.astroitsolutions.clienttracker.Exception.TransactionsExceptionHandler;
import com.astroitsolutions.clienttracker.Service.TransactionService;
import com.astroitsolutions.clienttracker.Utils.TestUtils;

@ExtendWith(MockitoExtension.class)
public class TransactionControllerImplTest {

    @InjectMocks
    private TransactionControllerImpl transactionControllerImpl;

    private MockMvc mockMvc;


    @Mock
    private TransactionService transactionService;

    private final TestUtils testUtils = new TestUtils();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(transactionControllerImpl)
                .setControllerAdvice(new TransactionsExceptionHandler())
                .build();
    }

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

        mockMvc.perform(get("/clienttracker/tansaction/find")
                .param("from", "12-1-2021")
                .param("to", "12-1-2022")
                .param("pageSize", "20")
                .param("pageNumber", "0"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("An error occurred when parsing date"));
    }
}
