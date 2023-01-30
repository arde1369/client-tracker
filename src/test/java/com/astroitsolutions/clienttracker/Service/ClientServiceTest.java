package com.astroitsolutions.clienttracker.Service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
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
import com.astroitsolutions.clienttracker.Repository.ClientRepository;
import com.astroitsolutions.clienttracker.Utils.ClientTestUtils;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {
    
    @Autowired
    @InjectMocks
    private ClientService clientService;

    @Mock
    private ClientRepository repository;

    ClientTestUtils clientTestUtils = new ClientTestUtils();

    @Test
    public void retrieveClientById_success(){

        Client mockClient = clientTestUtils.createNewCompleteClient();

        Optional<Client>  clientOptional= Optional.of(mockClient);
        Mockito.when(repository.findById(any())).thenReturn(clientOptional);

        Client c = clientService.retrieveClientById(1);

        assertNotNull(c);
    }
}
