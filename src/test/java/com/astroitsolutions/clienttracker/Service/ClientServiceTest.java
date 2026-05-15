package com.astroitsolutions.clienttracker.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import com.astroitsolutions.data_services.Dao.ClientDao;
import com.astroitsolutions.data_services.Dao.ProductDao;
import com.astroitsolutions.data_services.Dao.ReviewDao;
import com.astroitsolutions.data_services.Dao.TransactionDao;
import com.astroitsolutions.data_services.Entity.Client;
import com.astroitsolutions.data_services.Entity.Product;
import com.astroitsolutions.data_services.Entity.Review;
import com.astroitsolutions.data_services.Entity.Transaction;
import com.astroitsolutions.clienttracker.Utils.TestUtils;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @InjectMocks
    private ClientService clientService;


    @Mock
    private ClientDao clientRepository;


    @Mock
    private ProductDao productRepository;

    @Mock
    private TransactionDao transactionRepository;

    private final TestUtils testUtils = new TestUtils();

    @Mock
    private ReviewDao reviewRepository;

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
        Mockito.when(clientRepository.findById(anyInt())).thenReturn(clientOptional);

        Client c = clientService.retrieveClientById(1);

        assertNotNull(c);
        assertEquals(c, mockClient);
    }

    @Test
    public void retrieveClientById_null_noClientFoundById(){

        Optional<Client>  clientOptional= Optional.empty();
        Mockito.when(clientRepository.findById(anyInt())).thenReturn(clientOptional);

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

        Mockito.when(clientRepository.findById(anyInt())).thenReturn(clientOptional);
        Mockito.when(productRepository.findById(anyInt())).thenReturn(productOptional);

        assertTrue(clientService.addReviewForProductByClientId(mockClient.getId(), mockReview));
    }

    @Test
    public void addReviewForProductByClientIdTest_false_failure(){
        Client mockClient = testUtils.createNewCompleteClient();
        Optional<Client>  clientOptional = Optional.empty();

        Product mockProduct = testUtils.createProductList(null, mockClient).get(0);

        Review mockReview = testUtils.createReviewsList(mockProduct, mockClient).get(0);

        Mockito.when(clientRepository.findById(anyInt())).thenReturn(clientOptional);

        assertFalse(clientService.addReviewForProductByClientId(mockClient.getId(), mockReview));
    }

    @Test
    public void getReviewsAddedByClientById_success(){
        Client mockClient = testUtils.createNewCompleteClient();

        Mockito.when(reviewRepository.findAllByClientId(anyInt(), any(Pageable.class))).thenReturn(Optional.of(mockClient.getReviews()));

        List<Review> reviews = clientService.getReviewsAddedByClientById(1, 1, 1);

        assertNotNull(reviews);
        assertEquals(8, reviews.size());
    }

    @Test
    public void getReviewsAddedByClientById_null_noClientFoundById(){

        Mockito.when(reviewRepository.findAllByClientId(anyInt(), any(Pageable.class))).thenReturn(Optional.empty());

        List<Review> reviews = clientService.getReviewsAddedByClientById(1, 1, 1);

        assertNull(reviews);
    }

    @Test
    public void addTransactionForClientById_success(){
        Client mockClient = testUtils.createNewCompleteClient();
        Optional<Client>  clientOptional= Optional.of(mockClient);
        Transaction transaction = testUtils.createSingleTransaction(mockClient);

        Mockito.when(clientRepository.findById(anyInt())).thenReturn(clientOptional);

        boolean resultOfOperation = clientService.addTransactionForClientById(mockClient.getId(), transaction);

        assertTrue(resultOfOperation);
        assertTrue(mockClient.getTransactions().contains(transaction));
    }

    @Test
    public void addTransactionForClientByName_success(){
        Client mockClient = testUtils.createNewCompleteClient();
        Optional<Client>  clientOptional= Optional.of(mockClient);
        Transaction transaction = testUtils.createSingleTransaction(mockClient);

        Mockito.when(clientRepository.findByFirstnameAndLastname(anyString(), anyString())).thenReturn(clientOptional);

        boolean resultOfOperation = clientService.addTransactionForClientByFirstnameAndLastname(mockClient.getFirstname(), mockClient.getLastname(), transaction);

        assertTrue(resultOfOperation);
        assertTrue(mockClient.getTransactions().contains(transaction));
    }

    @Test
    public void addTransactionForClientById_null_noClientFoundById(){
        Client mockClient = testUtils.createNewCompleteClient();
        Optional<Client>  clientOptional= Optional.empty();
        Transaction transaction = testUtils.createSingleTransaction(mockClient);

        Mockito.when(clientRepository.findById(anyInt())).thenReturn(clientOptional);

        boolean resultOfOperation = clientService.addTransactionForClientById(mockClient.getId(), transaction);

        assertFalse(resultOfOperation);
        assertFalse(mockClient.getTransactions().contains(transaction));
    }

    @Test
    public void addTransactionForClientByName_null_noClientFoundByName(){
        Client mockClient = testUtils.createNewCompleteClient();
        Optional<Client>  clientOptional= Optional.empty();
        Transaction transaction = testUtils.createSingleTransaction(mockClient);

        Mockito.when(clientRepository.findByFirstnameAndLastname(anyString(), anyString())).thenReturn(clientOptional);

        boolean resultOfOperation = clientService.addTransactionForClientByFirstnameAndLastname(mockClient.getFirstname(), mockClient.getLastname(), transaction);

        assertFalse(resultOfOperation);
        assertFalse(mockClient.getTransactions().contains(transaction));
    }

    @Test
    public void getTransactionsByClientById_success(){
        Client mockClient = testUtils.createNewCompleteClient();

        Mockito.when(transactionRepository.findAllByClientId(anyInt(), any(Pageable.class))).thenReturn(Optional.of(mockClient.getTransactions()));

        List<Transaction> listOfTransactions = clientService.getTransactionsByClientById(mockClient.getId(), 20, 0);

        assertNotNull(listOfTransactions);
        assertEquals(mockClient.getTransactions().size(), listOfTransactions.size());
    }

    @Test
    public void getTransactionsByClientById_empty(){
        Mockito.when(transactionRepository.findAllByClientId(anyInt(), any(Pageable.class))).thenReturn(Optional.empty());

        List<Transaction> listOfTransactions = clientService.getTransactionsByClientById(1,2,1);

        assertNull(listOfTransactions);
    }

    @Test
    public void getTransactionsAddedByClientByFirstnameAndLastname_null_noClientFoundByName(){
        Client mockClient = testUtils.createNewCompleteClient();

        List<Transaction> listOfTransactions = clientService.getTransactionsByClientById(mockClient.getId(), 20, 0);

        assertNull(listOfTransactions);
    }

    @Test
    public void updateRatingForClientById_success(){
        Client mockClient = testUtils.createNewCompleteClient();
        Optional<Client>  clientOptional= Optional.of(mockClient);

        Mockito.when(clientRepository.findById(anyInt())).thenReturn(clientOptional);

        boolean resultOfOperation = clientService.updateRatingForClientById(mockClient.getId(), 1);

        assertTrue(resultOfOperation);
        assertEquals(3, mockClient.getRating());
    }

    
    @Test
    public void updateRatingForClientByFirstnameAndLastname_success(){
        Client mockClient = testUtils.createNewCompleteClient();
        Optional<Client>  clientOptional= Optional.of(mockClient);

        Mockito.when(clientRepository.findByFirstnameAndLastname(anyString(), anyString())).thenReturn(clientOptional);

        boolean resultOfOperation = clientService.updateRatingForClientByFirstnameAndLastname(mockClient.getFirstname(), mockClient.getLastname(), 1);

        assertTrue(resultOfOperation);
        assertEquals(3, mockClient.getRating());
    }

    @Test
    public void updateRatingForClientById_null_noClientFoundById(){
        Client mockClient = testUtils.createNewCompleteClient();
        Optional<Client>  clientOptional= Optional.empty();

        Mockito.when(clientRepository.findById(anyInt())).thenReturn(clientOptional);

        boolean resultOfOperation = clientService.updateRatingForClientById(mockClient.getId(), 1);

        assertFalse(resultOfOperation);
    }

    
    @Test
    public void updateRatingForClientByFirstnameAndLastname_null_noClientFoundByName(){
        Client mockClient = testUtils.createNewCompleteClient();
        Optional<Client>  clientOptional= Optional.empty();

        Mockito.when(clientRepository.findByFirstnameAndLastname(anyString(), anyString())).thenReturn(clientOptional);

        boolean resultOfOperation = clientService.updateRatingForClientByFirstnameAndLastname(mockClient.getFirstname(), mockClient.getLastname(), 1);

        assertFalse(resultOfOperation);
    }
}
