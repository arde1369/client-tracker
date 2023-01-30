package com.astroitsolutions.clienttracker.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

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
import com.astroitsolutions.clienttracker.Repository.ClientRepository;
import com.astroitsolutions.clienttracker.Repository.ProductRepository;
import com.astroitsolutions.clienttracker.Utils.ClientTestUtils;

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

    ClientTestUtils clientTestUtils = new ClientTestUtils();

    @Test
    public void retrieveClientByIdTest_200_success(){

        Client mockClient = clientTestUtils.createNewCompleteClient();

        Optional<Client>  clientOptional= Optional.of(mockClient);
        Mockito.when(clientRepository.findById(any())).thenReturn(clientOptional);

        Client c = clientService.retrieveClientById(1);

        assertNotNull(c);
        assertEquals(c, mockClient);
    }

    @Test
    public void retrieveClientById_200_null(){

        Optional<Client>  clientOptional= Optional.empty();
        Mockito.when(clientRepository.findById(any())).thenReturn(clientOptional);

        Client c = clientService.retrieveClientById(1);

        assertNull(c);
    }

    @Test
    public void retrieveClientByFirstnameAndLastnameTest_200_success(){
        Client mockClient = clientTestUtils.createNewCompleteClient();

        Optional<Client>  clientOptional= Optional.of(mockClient);
        Mockito.when(clientRepository.findByFirstnameAndLastname(any(), any())).thenReturn(clientOptional);

        Client c = clientService.retrieveClientByFirstnameAndLastname(Mockito.anyString(), Mockito.anyString());

        assertNotNull(c);
        assertEquals(c, mockClient);
    }

    @Test
    public void retrieveClientByFirstnameAndLastnameTest_200_null(){
        Optional<Client>  clientOptional= Optional.empty();
        Mockito.when(clientRepository.findByFirstnameAndLastname(any(), any())).thenReturn(clientOptional);

        Client c = clientService.retrieveClientByFirstnameAndLastname(Mockito.anyString(), Mockito.anyString());

        assertNull(c);
    }

    @Test
    public void addReviewForProductByClientIdTest_true_success(){
        Client mockClient = clientTestUtils.createNewCompleteClient();
        Optional<Client>  clientOptional= Optional.of(mockClient);

        Product mockProduct = clientTestUtils.createProductList(null, mockClient).get(0);
        Optional<Product>  productOptional = Optional.of(mockProduct);

        Review mockReview = clientTestUtils.createReviewsList(mockProduct, mockClient).get(0);

        Mockito.when(clientRepository.findById(any())).thenReturn(clientOptional);
        Mockito.when(productRepository.findById(any())).thenReturn(productOptional);

        assertTrue(clientService.addReviewForProductByClientId(mockClient.getId(), mockReview));
    }

    @Test
    public void addReviewForProductByClientIdTest_false_failure(){
        Client mockClient = clientTestUtils.createNewCompleteClient();
        Optional<Client>  clientOptional = Optional.empty();

        Product mockProduct = clientTestUtils.createProductList(null, mockClient).get(0);

        Review mockReview = clientTestUtils.createReviewsList(mockProduct, mockClient).get(0);

        Mockito.when(clientRepository.findById(any())).thenReturn(clientOptional);

        assertFalse(clientService.addReviewForProductByClientId(mockClient.getId(), mockReview));
    }
}
