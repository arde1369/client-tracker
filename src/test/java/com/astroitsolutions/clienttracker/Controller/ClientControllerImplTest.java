package com.astroitsolutions.clienttracker.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

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

import com.astroitsolutions.clienttracker.Entity.Client;
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
    public void getClient_Success_200() throws JsonProcessingException, Exception{
        Client mockClient = testUtils.createNewCompleteClient();
        
        when(clientService.retrieveClientById(anyInt())).thenReturn(mockClient);

        ResponseEntity<Client> responseEntity = clientControllerImpl.retrieveClientById(mockClient.getId());

        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
        assertEquals(HttpStatusCode.valueOf(200), responseEntity.getStatusCode());
        assertEquals(mockClient, responseEntity.getBody());
    }

    @Test
    public void getClient_Failure_400() throws JsonProcessingException, Exception{
        Client mockClient = testUtils.createNewCompleteClient();
        
        when(clientService.retrieveClientById(anyInt())).thenReturn(null);

        ResponseEntity<Client> responseEntity = clientControllerImpl.retrieveClientById(mockClient.getId());

        assertNotNull(responseEntity);
        assertNull(responseEntity.getBody());
        assertEquals(HttpStatusCode.valueOf(400), responseEntity.getStatusCode());
    }

    @Test
    public void getClient_Failure_500() throws JsonProcessingException, Exception{
        Client mockClient = testUtils.createNewCompleteClient();
        
        when(clientService.retrieveClientById(anyInt())).thenThrow(new NullPointerException());

        ResponseEntity<Client> responseEntity = clientControllerImpl.retrieveClientById(mockClient.getId());

        assertNotNull(responseEntity);
        assertNull(responseEntity.getBody());
        assertEquals(HttpStatusCode.valueOf(500), responseEntity.getStatusCode());
    }
}
