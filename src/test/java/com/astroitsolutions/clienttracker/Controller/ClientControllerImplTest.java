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
    }

    @Test
    public void retrieveClientById_Failure_500() throws JsonProcessingException, Exception{
        Client mockClient = testUtils.createNewCompleteClient();
        
        when(clientService.retrieveClientById(anyInt())).thenThrow(new NullPointerException());

        ResponseEntity<Client> responseEntity = clientControllerImpl.retrieveClientById(mockClient.getId());

        assertNotNull(responseEntity);
        assertNull(responseEntity.getBody());
        assertEquals(HttpStatusCode.valueOf(500), responseEntity.getStatusCode());
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
    }

    @Test
    public void retrieveClientByFirstnameAndLastname_Failure_500() throws JsonProcessingException, Exception{
        Client mockClient = testUtils.createNewCompleteClient();
        
        when(clientService.retrieveClientByFirstnameAndLastname(anyString(),anyString())).thenThrow(new NullPointerException());

        ResponseEntity<Client> responseEntity = clientControllerImpl.retrieveClientByFirstnameAndLastname(mockClient.getFirstname(), mockClient.getLastname());

        assertNotNull(responseEntity);
        assertNull(responseEntity.getBody());
        assertEquals(HttpStatusCode.valueOf(500), responseEntity.getStatusCode());
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
    }

    @Test
    public void addReviewForProductByClientId_Failure_500(){
        Review review = testUtils.createSingleReview(testUtils.createSingleProduct());

        when(clientService.addReviewForProductByClientId(anyInt(), any(Review.class))).thenThrow(new NullPointerException());

        ResponseEntity<HttpStatus> responseEntity = clientControllerImpl.addReviewForProductByClientId(0, review);

        assertNotNull(responseEntity);
        assertEquals(HttpStatusCode.valueOf(500), responseEntity.getStatusCode());
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
    public void getReviewsAddedByClientById_success_400(){
        when(clientService.getReviewsAddedByClientById(anyInt())).thenReturn(null);

        ResponseEntity<List<Review>> responseEntity = clientControllerImpl.getReviewsAddedByClientById(0);

        assertNotNull(responseEntity);
        assertEquals(HttpStatusCode.valueOf(400), responseEntity.getStatusCode());
    }

    @Test
    public void getReviewsAddedByClientById_success_500(){
        when(clientService.getReviewsAddedByClientById(anyInt())).thenThrow(new NullPointerException());

        ResponseEntity<List<Review>> responseEntity = clientControllerImpl.getReviewsAddedByClientById(0);

        assertNotNull(responseEntity);
        assertEquals(HttpStatusCode.valueOf(500), responseEntity.getStatusCode());
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
    public void getReviewsAddedByClientByFirstnameAndLastname_success_400(){
        when(clientService.getReviewsAddedByClientByFirstnameAndLastname(anyString(), anyString())).thenReturn(null);

        ResponseEntity<List<Review>> responseEntity = clientControllerImpl.getReviewsAddedByClientByFirstnameAndLastname("first", "last");

        assertNotNull(responseEntity);
        assertEquals(HttpStatusCode.valueOf(400), responseEntity.getStatusCode());
    }

    @Test
    public void getReviewsAddedByClientByFirstnameAndLastname_success_500(){
        when(clientService.getReviewsAddedByClientByFirstnameAndLastname(anyString(), anyString())).thenThrow(new NullPointerException());

        ResponseEntity<List<Review>> responseEntity = clientControllerImpl.getReviewsAddedByClientByFirstnameAndLastname("first", "last");

        assertNotNull(responseEntity);
        assertEquals(HttpStatusCode.valueOf(500), responseEntity.getStatusCode());
    }
}
