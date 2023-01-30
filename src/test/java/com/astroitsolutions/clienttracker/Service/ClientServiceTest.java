package com.astroitsolutions.clienttracker.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.astroitsolutions.clienttracker.Entity.Client;
import com.astroitsolutions.clienttracker.Entity.Product;
import com.astroitsolutions.clienttracker.Entity.Review;
import com.astroitsolutions.clienttracker.Entity.Transaction;
import com.astroitsolutions.clienttracker.Repository.ClientRepository;
import com.astroitsolutions.clienttracker.Repository.ProductRepository;
import com.astroitsolutions.clienttracker.Utils.TestUtils;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {
    
    @Autowired
    @InjectMocks
    private ClientService clientService;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ProductRepository productRepository;

    TestUtils testUtils = new TestUtils();

    @Test
    public void addClient_success(){
        Client mockClient = testUtils.createNewCompleteClient();

        Mockito.when(clientRepository.save(mockClient)).thenReturn(mockClient);

        Client client = clientService.addClient(mockClient);

        assertNotNull(client);
        assertEquals(client, mockClient);
    }

    @Test
    public void retrieveClientByIdTest_success(){

        Client mockClient = testUtils.createNewCompleteClient();

        Optional<Client>  clientOptional= Optional.of(mockClient);
        Mockito.when(clientRepository.findById(any())).thenReturn(clientOptional);

        Client c = clientService.retrieveClientById(1);

        assertNotNull(c);
        assertEquals(c, mockClient);
    }

    @Test
    public void retrieveClientById_null_noClientFoundById(){

        Optional<Client>  clientOptional= Optional.empty();
        Mockito.when(clientRepository.findById(any())).thenReturn(clientOptional);

        Client c = clientService.retrieveClientById(1);

        assertNull(c);
    }

    @Test
    public void retrieveClientByFirstnameAndLastnameTest_success(){
        Client mockClient = testUtils.createNewCompleteClient();

        Optional<Client>  clientOptional= Optional.of(mockClient);
        Mockito.when(clientRepository.findByFirstnameAndLastname(any(), any())).thenReturn(clientOptional);

        Client c = clientService.retrieveClientByFirstnameAndLastname(Mockito.anyString(), Mockito.anyString());

        assertNotNull(c);
        assertEquals(c, mockClient);
    }

    @Test
    public void retrieveClientByFirstnameAndLastnameTest__noClientFoundByName(){
        Optional<Client>  clientOptional= Optional.empty();
        Mockito.when(clientRepository.findByFirstnameAndLastname(any(), any())).thenReturn(clientOptional);

        Client c = clientService.retrieveClientByFirstnameAndLastname(Mockito.anyString(), Mockito.anyString());

        assertNull(c);
    }

    @Test
    public void addReviewForProductByClientIdTest_true_success(){
        Client mockClient = testUtils.createNewCompleteClient();
        Optional<Client>  clientOptional= Optional.of(mockClient);

        Product mockProduct = testUtils.createProductList(null, mockClient).get(0);
        Optional<Product>  productOptional = Optional.of(mockProduct);

        Review mockReview = testUtils.createReviewsList(mockProduct, mockClient).get(0);

        Mockito.when(clientRepository.findById(any())).thenReturn(clientOptional);
        Mockito.when(productRepository.findById(any())).thenReturn(productOptional);

        assertTrue(clientService.addReviewForProductByClientId(mockClient.getId(), mockReview));
    }

    @Test
    public void addReviewForProductByClientIdTest_false_failure(){
        Client mockClient = testUtils.createNewCompleteClient();
        Optional<Client>  clientOptional = Optional.empty();

        Product mockProduct = testUtils.createProductList(null, mockClient).get(0);

        Review mockReview = testUtils.createReviewsList(mockProduct, mockClient).get(0);

        Mockito.when(clientRepository.findById(any())).thenReturn(clientOptional);

        assertFalse(clientService.addReviewForProductByClientId(mockClient.getId(), mockReview));
    }

    @Test
    public void getReviewsAddedByClientById_success(){
        Client mockClient = testUtils.createNewCompleteClient();
        Optional<Client>  clientOptional= Optional.of(mockClient);

        Mockito.when(clientRepository.findById(any())).thenReturn(clientOptional);

        List<Review> reviews = clientService.getReviewsAddedByClientById(Mockito.anyInt());

        assertNotNull(reviews);
        assertEquals(8, reviews.size());
    }

    @Test
    public void getReviewsAddedByClientByFirstnameAndLastname_success(){
        Client mockClient = testUtils.createNewCompleteClient();
        Optional<Client>  clientOptional= Optional.of(mockClient);

        Mockito.when(clientRepository.findByFirstnameAndLastname(any(), any())).thenReturn(clientOptional);

        List<Review> reviews = clientService.getReviewsAddedByClientByFirstnameAndLastname(anyString(), anyString());

        assertNotNull(reviews);
        assertEquals(8, reviews.size());
    }

    @Test
    public void getReviewsAddedByClientById_null_noClientFoundById(){
        Optional<Client>  clientOptional= Optional.empty();

        Mockito.when(clientRepository.findById(any())).thenReturn(clientOptional);

        List<Review> reviews = clientService.getReviewsAddedByClientById(Mockito.anyInt());

        assertNull(reviews);
    }

    @Test
    public void getReviewsAddedByClientByFirstnameAndLastname_null_noClientFoundByName(){
        Optional<Client>  clientOptional= Optional.empty();

        Mockito.when(clientRepository.findByFirstnameAndLastname(any(), any())).thenReturn(clientOptional);

        List<Review> reviews = clientService.getReviewsAddedByClientByFirstnameAndLastname(anyString(), anyString());

        assertNull(reviews);
    }

    @Test
    public void addTransactionForClientById_success(){
        Client mockClient = testUtils.createNewCompleteClient();
        Optional<Client>  clientOptional= Optional.of(mockClient);
        Transaction transaction = testUtils.createSingleTransaction(mockClient);

        Mockito.when(clientRepository.findById(any())).thenReturn(clientOptional);

        boolean resultOfOperation = clientService.addTransactionForClientById(mockClient.getId(), transaction);

        assertTrue(resultOfOperation);
        assertTrue(mockClient.getTransactions().contains(transaction));
    }

    @Test
    public void addTransactionForClientByName_success(){
        Client mockClient = testUtils.createNewCompleteClient();
        Optional<Client>  clientOptional= Optional.of(mockClient);
        Transaction transaction = testUtils.createSingleTransaction(mockClient);

        Mockito.when(clientRepository.findByFirstnameAndLastname(any(), any())).thenReturn(clientOptional);

        boolean resultOfOperation = clientService.addTransactionForClientByFirstnameAndLastname(mockClient.getFirstname(), mockClient.getLastname(), transaction);

        assertTrue(resultOfOperation);
        assertTrue(mockClient.getTransactions().contains(transaction));
    }

    @Test
    public void addTransactionForClientById_null_noClientFoundById(){
        Client mockClient = testUtils.createNewCompleteClient();
        Optional<Client>  clientOptional= Optional.empty();
        Transaction transaction = testUtils.createSingleTransaction(mockClient);

        Mockito.when(clientRepository.findById(any())).thenReturn(clientOptional);

        boolean resultOfOperation = clientService.addTransactionForClientById(mockClient.getId(), transaction);

        assertFalse(resultOfOperation);
        assertFalse(mockClient.getTransactions().contains(transaction));
    }

    @Test
    public void addTransactionForClientByName_null_noClientFoundByName(){
        Client mockClient = testUtils.createNewCompleteClient();
        Optional<Client>  clientOptional= Optional.empty();
        Transaction transaction = testUtils.createSingleTransaction(mockClient);

        Mockito.when(clientRepository.findByFirstnameAndLastname(any(), any())).thenReturn(clientOptional);

        boolean resultOfOperation = clientService.addTransactionForClientByFirstnameAndLastname(mockClient.getFirstname(), mockClient.getLastname(), transaction);

        assertFalse(resultOfOperation);
        assertFalse(mockClient.getTransactions().contains(transaction));
    }

    @Test
    public void getTransactionsByClientById_success(){
        Client mockClient = testUtils.createNewCompleteClient();
        Optional<Client>  clientOptional= Optional.of(mockClient);

        Mockito.when(clientRepository.findById(any())).thenReturn(clientOptional);

        List<Transaction> listOfTransactions = clientService.getTransactionsByClientById(mockClient.getId());

        assertNotNull(listOfTransactions);
        assertEquals(mockClient.getTransactions().size(), listOfTransactions.size());
    }

    @Test
    public void getTransactionsAddedByClientByFirstnameAndLastname_success(){
        Client mockClient = testUtils.createNewCompleteClient();
        Optional<Client>  clientOptional= Optional.of(mockClient);

        Mockito.when(clientRepository.findByFirstnameAndLastname(any(), any())).thenReturn(clientOptional);

        List<Transaction> listOfTransactions = clientService.getTransactionsAddedByClientByFirstnameAndLastname(mockClient.getFirstname(), mockClient.getLastname());

        assertNotNull(listOfTransactions);
        assertEquals(mockClient.getTransactions().size(), listOfTransactions.size());
    }

    @Test
    public void getTransactionsByClientById_null_noClientFoundById(){
        Client mockClient = testUtils.createNewCompleteClient();
        Optional<Client>  clientOptional= Optional.empty();

        Mockito.when(clientRepository.findById(any())).thenReturn(clientOptional);

        List<Transaction> listOfTransactions = clientService.getTransactionsByClientById(mockClient.getId());

        assertNull(listOfTransactions);
    }

    @Test
    public void getTransactionsAddedByClientByFirstnameAndLastname_null_noClientFoundByName(){
        Client mockClient = testUtils.createNewCompleteClient();
        Optional<Client>  clientOptional= Optional.empty();

        Mockito.when(clientRepository.findByFirstnameAndLastname(any(), any())).thenReturn(clientOptional);

        List<Transaction> listOfTransactions = clientService.getTransactionsAddedByClientByFirstnameAndLastname(mockClient.getFirstname(), mockClient.getLastname());

        assertNull(listOfTransactions);
    }

    @Test
    public void updateRatingForClientById_success(){
        Client mockClient = testUtils.createNewCompleteClient();
        Optional<Client>  clientOptional= Optional.of(mockClient);

        Mockito.when(clientRepository.findById(any())).thenReturn(clientOptional);

        boolean resultOfOperation = clientService.updateRatingForClientById(mockClient.getId(), 1);

        assertTrue(resultOfOperation);
        assertEquals(3, mockClient.getRating());
    }

    
    @Test
    public void updateRatingForClientByFirstnameAndLastname_success(){
        Client mockClient = testUtils.createNewCompleteClient();
        Optional<Client>  clientOptional= Optional.of(mockClient);

        Mockito.when(clientRepository.findByFirstnameAndLastname(any(), any())).thenReturn(clientOptional);

        boolean resultOfOperation = clientService.updateRatingForClientByFirstnameAndLastname(mockClient.getFirstname(), mockClient.getLastname(), 1);

        assertTrue(resultOfOperation);
        assertEquals(3, mockClient.getRating());
    }

    @Test
    public void updateRatingForClientById_null_noClientFoundById(){
        Client mockClient = testUtils.createNewCompleteClient();
        Optional<Client>  clientOptional= Optional.empty();

        Mockito.when(clientRepository.findById(any())).thenReturn(clientOptional);

        boolean resultOfOperation = clientService.updateRatingForClientById(mockClient.getId(), 1);

        assertFalse(resultOfOperation);
    }

    
    @Test
    public void updateRatingForClientByFirstnameAndLastname_null_noClientFoundByName(){
        Client mockClient = testUtils.createNewCompleteClient();
        Optional<Client>  clientOptional= Optional.empty();

        Mockito.when(clientRepository.findByFirstnameAndLastname(any(), any())).thenReturn(clientOptional);

        boolean resultOfOperation = clientService.updateRatingForClientByFirstnameAndLastname(mockClient.getFirstname(), mockClient.getLastname(), 1);

        assertFalse(resultOfOperation);
    }
}
