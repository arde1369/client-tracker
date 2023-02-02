package com.astroitsolutions.clienttracker.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.astroitsolutions.clienttracker.Entity.Client;
import com.astroitsolutions.clienttracker.Entity.Review;
import com.astroitsolutions.clienttracker.Entity.Transaction;
import com.astroitsolutions.clienttracker.Service.ClientService;
import com.astroitsolutions.clienttracker.Utils.TestUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
public class ClientControllerImplTest {
    
    @Autowired
    @InjectMocks
    private ClientControllerImpl clientControllerImpl;

    @Mock
    private ClientService clientService;

    TestUtils testUtils = new TestUtils();

    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void addClient_Success_201() throws JsonProcessingException, Exception{
        Client mockClient = testUtils.createNewCompleteClient();
        
        when(clientService.addClient(any(Client.class))).thenReturn(mockClient);

        ResponseEntity<Client> responseEntity = clientControllerImpl.addClient(mockClient);

        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
        assertEquals(HttpStatusCode.valueOf(201), responseEntity.getStatusCode());
        assertEquals(mockClient, responseEntity.getBody());
    }

    @Test
    public void addClient_failure_500() throws JsonProcessingException, Exception{
        Client mockClient = testUtils.createNewCompleteClient();
        
        when(clientService.addClient(any(Client.class))).thenThrow(new NullPointerException());

        ResponseEntity<Client> responseEntity = clientControllerImpl.addClient(mockClient);

        assertNotNull(responseEntity);
        assertNull(responseEntity.getBody());
        assertEquals(HttpStatusCode.valueOf(500), responseEntity.getStatusCode());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), responseEntity.getHeaders().get("error-message").get(0));
    }

    @Test
    public void retrieveClientById_Success_200() throws JsonProcessingException, Exception{
        Client mockClient = testUtils.createNewCompleteClient();
        
        when(clientService.retrieveClientById(anyInt())).thenReturn(mockClient);

        ResponseEntity<Client> responseEntity = clientControllerImpl.retrieveClientById(mockClient.getId());

        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
        assertEquals(HttpStatusCode.valueOf(200), responseEntity.getStatusCode());
        assertEquals(mockClient, responseEntity.getBody());
    }

    @Test
    public void retrieveClientById_Failure_400() throws JsonProcessingException, Exception{
        Client mockClient = testUtils.createNewCompleteClient();
        
        when(clientService.retrieveClientById(anyInt())).thenReturn(null);

        ResponseEntity<Client> responseEntity = clientControllerImpl.retrieveClientById(mockClient.getId());

        assertNotNull(responseEntity);
        assertNull(responseEntity.getBody());
        assertEquals(HttpStatusCode.valueOf(400), responseEntity.getStatusCode());
        assertEquals(HttpStatus.NOT_FOUND.getReasonPhrase(), responseEntity.getHeaders().get("error-message").get(0));
    }

    @Test
    public void retrieveClientById_Failure_500() throws JsonProcessingException, Exception{
        Client mockClient = testUtils.createNewCompleteClient();
        
        when(clientService.retrieveClientById(anyInt())).thenThrow(new NullPointerException());

        ResponseEntity<Client> responseEntity = clientControllerImpl.retrieveClientById(mockClient.getId());

        assertNotNull(responseEntity);
        assertNull(responseEntity.getBody());
        assertEquals(HttpStatusCode.valueOf(500), responseEntity.getStatusCode());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), responseEntity.getHeaders().get("error-message").get(0));
    }

    @Test
    public void retrieveClientByFirstnameAndLastname_Success_200() throws JsonProcessingException, Exception{
        Client mockClient = testUtils.createNewCompleteClient();
        
        when(clientService.retrieveClientByFirstnameAndLastname(anyString(),anyString())).thenReturn(mockClient);

        ResponseEntity<Client> responseEntity = clientControllerImpl.retrieveClientByFirstnameAndLastname(mockClient.getFirstname(), mockClient.getLastname());

        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
        assertEquals(HttpStatusCode.valueOf(200), responseEntity.getStatusCode());
        assertEquals(mockClient, responseEntity.getBody());
    }

    @Test
    public void retrieveClientByFirstnameAndLastname_Failure_400() throws JsonProcessingException, Exception{
        Client mockClient = testUtils.createNewCompleteClient();
        
        when(clientService.retrieveClientByFirstnameAndLastname(anyString(),anyString())).thenReturn(null);

        ResponseEntity<Client> responseEntity = clientControllerImpl.retrieveClientByFirstnameAndLastname(mockClient.getFirstname(), mockClient.getLastname());

        assertNotNull(responseEntity);
        assertNull(responseEntity.getBody());
        assertEquals(HttpStatusCode.valueOf(400), responseEntity.getStatusCode());
        assertEquals(HttpStatus.NOT_FOUND.getReasonPhrase(), responseEntity.getHeaders().get("error-message").get(0));
    }

    @Test
    public void retrieveClientByFirstnameAndLastname_Failure_500() throws JsonProcessingException, Exception{
        Client mockClient = testUtils.createNewCompleteClient();
        
        when(clientService.retrieveClientByFirstnameAndLastname(anyString(),anyString())).thenThrow(new NullPointerException());

        ResponseEntity<Client> responseEntity = clientControllerImpl.retrieveClientByFirstnameAndLastname(mockClient.getFirstname(), mockClient.getLastname());

        assertNotNull(responseEntity);
        assertNull(responseEntity.getBody());
        assertEquals(HttpStatusCode.valueOf(500), responseEntity.getStatusCode());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), responseEntity.getHeaders().get("error-message").get(0));
    }

    @Test
    public void addReviewForProductByClientId_Success_200(){
        Review review = testUtils.createSingleReview(testUtils.createSingleProduct());

        when(clientService.addReviewForProductByClientId(anyInt(), any(Review.class))).thenReturn(true);

        ResponseEntity<HttpStatus> responseEntity = clientControllerImpl.addReviewForProductByClientId(0, review);

        assertNotNull(responseEntity);
        assertEquals(HttpStatusCode.valueOf(200), responseEntity.getStatusCode());
    }

    @Test
    public void addReviewForProductByClientId_Failure_400(){
        Review review = testUtils.createSingleReview(testUtils.createSingleProduct());

        when(clientService.addReviewForProductByClientId(anyInt(), any(Review.class))).thenReturn(false);

        ResponseEntity<HttpStatus> responseEntity = clientControllerImpl.addReviewForProductByClientId(0, review);

        assertNotNull(responseEntity);
        assertNull(responseEntity.getBody());
        assertEquals(HttpStatusCode.valueOf(400), responseEntity.getStatusCode());
        assertEquals(HttpStatus.NOT_FOUND.getReasonPhrase(), responseEntity.getHeaders().get("error-message").get(0));
    }

    @Test
    public void addReviewForProductByClientId_Failure_500(){
        Review review = testUtils.createSingleReview(testUtils.createSingleProduct());

        when(clientService.addReviewForProductByClientId(anyInt(), any(Review.class))).thenThrow(new NullPointerException());

        ResponseEntity<HttpStatus> responseEntity = clientControllerImpl.addReviewForProductByClientId(0, review);

        assertNotNull(responseEntity);
        assertEquals(HttpStatusCode.valueOf(500), responseEntity.getStatusCode());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), responseEntity.getHeaders().get("error-message").get(0));
    }

    @Test
    public void getReviewsAddedByClientById_success_200(){
        List<Review> retrievedClientReviews = testUtils.createReviewsList(testUtils.createSingleProduct(), testUtils.createNewCompleteClient());

        when(clientService.getReviewsAddedByClientById(anyInt())).thenReturn(retrievedClientReviews);

        ResponseEntity<List<Review>> responseEntity = clientControllerImpl.getReviewsAddedByClientById(0);

        assertNotNull(responseEntity);
        assertEquals(HttpStatusCode.valueOf(200), responseEntity.getStatusCode());
        assertEquals(retrievedClientReviews, responseEntity.getBody());
    }

    @Test
    public void getReviewsAddedByClientById_failure_400(){
        when(clientService.getReviewsAddedByClientById(anyInt())).thenReturn(null);

        ResponseEntity<List<Review>> responseEntity = clientControllerImpl.getReviewsAddedByClientById(0);

        assertNotNull(responseEntity);
        assertEquals(HttpStatusCode.valueOf(400), responseEntity.getStatusCode());
        assertEquals(HttpStatus.NOT_FOUND.getReasonPhrase(), responseEntity.getHeaders().get("error-message").get(0));
    }

    @Test
    public void getReviewsAddedByClientById_failure_500(){
        when(clientService.getReviewsAddedByClientById(anyInt())).thenThrow(new NullPointerException());

        ResponseEntity<List<Review>> responseEntity = clientControllerImpl.getReviewsAddedByClientById(0);

        assertNotNull(responseEntity);
        assertEquals(HttpStatusCode.valueOf(500), responseEntity.getStatusCode());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), responseEntity.getHeaders().get("error-message").get(0));
    }

    @Test
    public void getReviewsAddedByClientByFirstnameAndLastname_success_200(){
        List<Review> retrievedClientReviews = testUtils.createReviewsList(testUtils.createSingleProduct(), testUtils.createNewCompleteClient());

        when(clientService.getReviewsAddedByClientByFirstnameAndLastname(anyString(), anyString())).thenReturn(retrievedClientReviews);

        ResponseEntity<List<Review>> responseEntity = clientControllerImpl.getReviewsAddedByClientByFirstnameAndLastname("first", "last");

        assertNotNull(responseEntity);
        assertEquals(HttpStatusCode.valueOf(200), responseEntity.getStatusCode());
        assertEquals(retrievedClientReviews, responseEntity.getBody());    
    }

    @Test
    public void getReviewsAddedByClientByFirstnameAndLastname_failure_400(){
        when(clientService.getReviewsAddedByClientByFirstnameAndLastname(anyString(), anyString())).thenReturn(null);

        ResponseEntity<List<Review>> responseEntity = clientControllerImpl.getReviewsAddedByClientByFirstnameAndLastname("first", "last");

        assertNotNull(responseEntity);
        assertEquals(HttpStatusCode.valueOf(400), responseEntity.getStatusCode());
        assertEquals(HttpStatus.NOT_FOUND.getReasonPhrase(), responseEntity.getHeaders().get("error-message").get(0));
    }

    @Test
    public void getReviewsAddedByClientByFirstnameAndLastname_failure_500(){
        when(clientService.getReviewsAddedByClientByFirstnameAndLastname(anyString(), anyString())).thenThrow(new NullPointerException());

        ResponseEntity<List<Review>> responseEntity = clientControllerImpl.getReviewsAddedByClientByFirstnameAndLastname("first", "last");

        assertNotNull(responseEntity);
        assertEquals(HttpStatusCode.valueOf(500), responseEntity.getStatusCode());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), responseEntity.getHeaders().get("error-message").get(0));
    }

    @Test
    public void addTransactionForClientById_successs_200(){
        Transaction transaction = testUtils.createSingleTransaction(testUtils.createNewCompleteClient());

        when(clientService.addTransactionForClientById(anyInt(), any(Transaction.class))).thenReturn(true);

        ResponseEntity<HttpStatus> responseEntity = clientControllerImpl.addTransactionForClientById(0, transaction);

        assertNotNull(responseEntity);
        assertEquals(HttpStatusCode.valueOf(200), responseEntity.getStatusCode());
    }

    @Test
    public void addTransactionForClientById_failure_400(){
        Transaction transaction = testUtils.createSingleTransaction(testUtils.createNewCompleteClient());

        when(clientService.addTransactionForClientById(anyInt(), any(Transaction.class))).thenReturn(false);

        ResponseEntity<HttpStatus> responseEntity = clientControllerImpl.addTransactionForClientById(0, transaction);

        assertNotNull(responseEntity);
        assertEquals(HttpStatusCode.valueOf(400), responseEntity.getStatusCode());
        assertEquals(HttpStatus.NOT_FOUND.getReasonPhrase(), responseEntity.getHeaders().get("error-message").get(0));
    }

    @Test
    public void addTransactionForClientById_failure_500(){
        Transaction transaction = testUtils.createSingleTransaction(testUtils.createNewCompleteClient());

        when(clientService.addTransactionForClientById(anyInt(), any(Transaction.class))).thenThrow(new NullPointerException());

        ResponseEntity<HttpStatus> responseEntity = clientControllerImpl.addTransactionForClientById(0, transaction);

        assertNotNull(responseEntity);
        assertEquals(HttpStatusCode.valueOf(500), responseEntity.getStatusCode());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), responseEntity.getHeaders().get("error-message").get(0));
    }

    @Test
    public void getTransactionsByClientById_succes_200(){
        List<Transaction> retrievedClientTransactions = testUtils.createTransactionList(testUtils.createNewCompleteClient());

        when(clientService.getTransactionsByClientById(anyInt())).thenReturn(retrievedClientTransactions);

        ResponseEntity<List<Transaction>> responseEntity = clientControllerImpl.getTransactionsByClientById(0);

        assertNotNull(responseEntity);
        assertEquals(HttpStatusCode.valueOf(200), responseEntity.getStatusCode());
        assertEquals(retrievedClientTransactions, responseEntity.getBody());
    }

    @Test
    public void getTransactionsByClientById_failure_400(){
        when(clientService.getTransactionsByClientById(anyInt())).thenReturn(null);

        ResponseEntity<List<Transaction>> responseEntity = clientControllerImpl.getTransactionsByClientById(0);

        assertNotNull(responseEntity);
        assertEquals(HttpStatusCode.valueOf(400), responseEntity.getStatusCode());
        assertEquals(HttpStatus.NOT_FOUND.getReasonPhrase(), responseEntity.getHeaders().get("error-message").get(0));
    }

    @Test
    public void getTransactionsByClientById_failure_500(){
        when(clientService.getTransactionsByClientById(anyInt())).thenThrow(new NullPointerException());

        ResponseEntity<List<Transaction>> responseEntity = clientControllerImpl.getTransactionsByClientById(0);

        assertNotNull(responseEntity);
        assertEquals(HttpStatusCode.valueOf(500), responseEntity.getStatusCode());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), responseEntity.getHeaders().get("error-message").get(0));
    }

    @Test
    public void getTransactionsAddedByClientByFirstnameAndLastname_succes_200(){
        List<Transaction> retrievedClientTransactions = testUtils.createTransactionList(testUtils.createNewCompleteClient());

        when(clientService.getTransactionsAddedByClientByFirstnameAndLastname(anyString(), anyString())).thenReturn(retrievedClientTransactions);

        ResponseEntity<List<Transaction>> responseEntity = clientControllerImpl.getTransactionsAddedByClientByFirstnameAndLastname("first", "last");

        assertNotNull(responseEntity);
        assertEquals(HttpStatusCode.valueOf(200), responseEntity.getStatusCode());
        assertEquals(retrievedClientTransactions, responseEntity.getBody());
    }

    @Test
    public void getTransactionsAddedByClientByFirstnameAndLastname_failure_400(){
        when(clientService.getTransactionsAddedByClientByFirstnameAndLastname(anyString(), anyString())).thenReturn(null);

        ResponseEntity<List<Transaction>> responseEntity = clientControllerImpl.getTransactionsAddedByClientByFirstnameAndLastname("first", "last");

        assertNotNull(responseEntity);
        assertEquals(HttpStatusCode.valueOf(400), responseEntity.getStatusCode());
        assertEquals(HttpStatus.NOT_FOUND.getReasonPhrase(), responseEntity.getHeaders().get("error-message").get(0));
    }

    @Test
    public void getTransactionsAddedByClientByFirstnameAndLastname_failure_500(){
        when(clientService.getTransactionsAddedByClientByFirstnameAndLastname(anyString(), anyString())).thenThrow(new NullPointerException());

        ResponseEntity<List<Transaction>> responseEntity = clientControllerImpl.getTransactionsAddedByClientByFirstnameAndLastname("first", "last");

        assertNotNull(responseEntity);
        assertEquals(HttpStatusCode.valueOf(500), responseEntity.getStatusCode());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), responseEntity.getHeaders().get("error-message").get(0));
    }

    @Test
    public void updateRatingForClientById_success_200(){
        when(clientService.updateRatingForClientById(anyInt(), anyInt())).thenReturn(true);

        ResponseEntity<HttpStatus> responseEntity = clientControllerImpl.updateRatingForClientById(0, 5);

        assertNotNull(responseEntity);
        assertEquals(HttpStatusCode.valueOf(200), responseEntity.getStatusCode());
    }

    @Test
    public void updateRatingForClientById_failure_400(){
        when(clientService.updateRatingForClientById(anyInt(), anyInt())).thenReturn(false);

        ResponseEntity<HttpStatus> responseEntity = clientControllerImpl.updateRatingForClientById(0, 5);

        assertNotNull(responseEntity);
        assertEquals(HttpStatusCode.valueOf(400), responseEntity.getStatusCode());
        assertEquals(HttpStatus.NOT_FOUND.getReasonPhrase(), responseEntity.getHeaders().get("error-message").get(0));
    }

    @Test
    public void updateRatingForClientById_failure_500(){
        when(clientService.updateRatingForClientById(anyInt(), anyInt())).thenThrow(new NullPointerException());

        ResponseEntity<HttpStatus> responseEntity = clientControllerImpl.updateRatingForClientById(0, 5);

        assertNotNull(responseEntity);
        assertEquals(HttpStatusCode.valueOf(500), responseEntity.getStatusCode());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), responseEntity.getHeaders().get("error-message").get(0));
    }

    @Test
    public void updateRatingForClientByFirstnameAndLastname_success_200(){
        when(clientService.updateRatingForClientByFirstnameAndLastname(anyString(), anyString(), anyInt())).thenReturn(true);

        ResponseEntity<HttpStatus> responseEntity = clientControllerImpl.updateRatingForClientByFirstnameAndLastname("first", "last", 5);

        assertNotNull(responseEntity);
        assertEquals(HttpStatusCode.valueOf(200), responseEntity.getStatusCode());
    }

    @Test
    public void updateRatingForClientByFirstnameAndLastname_failure_400(){
        when(clientService.updateRatingForClientByFirstnameAndLastname(anyString(), anyString(), anyInt())).thenReturn(false);

        ResponseEntity<HttpStatus> responseEntity = clientControllerImpl.updateRatingForClientByFirstnameAndLastname("first", "last", 5);

        assertNotNull(responseEntity);
        assertEquals(HttpStatusCode.valueOf(400), responseEntity.getStatusCode());
        assertEquals(HttpStatus.NOT_FOUND.getReasonPhrase(), responseEntity.getHeaders().get("error-message").get(0));
    }

    @Test
    public void updateRatingForClientByFirstnameAndLastname_failure_500(){
        when(clientService.updateRatingForClientByFirstnameAndLastname(anyString(), anyString(), anyInt())).thenThrow(new NullPointerException());

        ResponseEntity<HttpStatus> responseEntity = clientControllerImpl.updateRatingForClientByFirstnameAndLastname("first", "last", 5);

        assertNotNull(responseEntity);
        assertEquals(HttpStatusCode.valueOf(500), responseEntity.getStatusCode());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), responseEntity.getHeaders().get("error-message").get(0));
    }
}
