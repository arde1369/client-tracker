package com.astroitsolutions.clienttracker.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.astroitsolutions.clienttracker.Entity.Client;
import com.astroitsolutions.clienttracker.Entity.Company;
import com.astroitsolutions.clienttracker.Service.CompanyService;
import com.astroitsolutions.clienttracker.Utils.TestUtils;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CompanyControllerImplTest {

    @Autowired
    @InjectMocks
    private CompanyControllerImpl companyControllerImpl;

    @Mock
    private CompanyService companyService;

    private TestUtils testUtils = new TestUtils();
    
    @Test
    public void addOrUpdateCompany_success_201(){
        Client mockClient = testUtils.createNewCompleteClient();
        Company mockCompany = testUtils.createNewMedSizeCompany(mockClient);

        when(companyService.addOrUpdateCompany(any())).thenReturn(mockCompany);

        ResponseEntity<Company> responseEntity = companyControllerImpl.addOrUpdateCompany(mockCompany);

        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
        assertEquals(HttpStatusCode.valueOf(201), responseEntity.getStatusCode());
        assertEquals(mockCompany, responseEntity.getBody());
    }

    @Test
    public void addOrUpdateCompany_failure_500(){
        Client mockClient = testUtils.createNewCompleteClient();
        Company mockCompany = testUtils.createNewMedSizeCompany(mockClient);

        when(companyService.addOrUpdateCompany(any())).thenThrow(new NullPointerException());

        ResponseEntity<Company> responseEntity = companyControllerImpl.addOrUpdateCompany(mockCompany);

        assertNotNull(responseEntity);
        assertNull(responseEntity.getBody());
        assertEquals(HttpStatusCode.valueOf(500), responseEntity.getStatusCode());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), responseEntity.getHeaders().get("error-message").get(0));
    }

    @Test
    public void getCompanyById_success_200(){
        Client mockClient = testUtils.createNewCompleteClient();
        Company mockCompany = testUtils.createNewMedSizeCompany(mockClient);

        when(companyService.getCompanyById(anyInt())).thenReturn(mockCompany);

        ResponseEntity<Company> responseEntity = companyControllerImpl.getCompanyById(1);

        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
        assertEquals(HttpStatusCode.valueOf(200), responseEntity.getStatusCode());
        assertEquals(mockCompany, responseEntity.getBody());
    }

    
    @Test
    public void getCompanyById_failure_404(){

        when(companyService.getCompanyById(anyInt())).thenReturn(null);

        ResponseEntity<Company> responseEntity = companyControllerImpl.getCompanyById(1);

        assertNotNull(responseEntity);
        assertNull(responseEntity.getBody());
        assertEquals(HttpStatusCode.valueOf(400), responseEntity.getStatusCode());
        assertEquals(HttpStatus.NOT_FOUND.getReasonPhrase(), responseEntity.getHeaders().get("error-message").get(0));
    }

    
    @Test
    public void getCompanyById_failure_500(){
        when(companyService.getCompanyById(anyInt())).thenThrow(new NullPointerException());

        ResponseEntity<Company> responseEntity = companyControllerImpl.getCompanyById(1);

        assertNotNull(responseEntity);
        assertNull(responseEntity.getBody());
        assertEquals(HttpStatusCode.valueOf(500), responseEntity.getStatusCode());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), responseEntity.getHeaders().get("error-message").get(0));
    }

    @Test
    public void getCompanyByName_success_200(){
        Client mockClient = testUtils.createNewCompleteClient();
        Company mockCompany = testUtils.createNewMedSizeCompany(mockClient);

        when(companyService.getCompanyByName(anyString())).thenReturn(mockCompany);

        ResponseEntity<Company> responseEntity = companyControllerImpl.getCompanyByName("test");

        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
        assertEquals(HttpStatusCode.valueOf(200), responseEntity.getStatusCode());
        assertEquals(mockCompany, responseEntity.getBody());
    }

    @Test
    public void getCompanyByName_failure_404(){
        when(companyService.getCompanyByName(anyString())).thenReturn(null);

        ResponseEntity<Company> responseEntity = companyControllerImpl.getCompanyByName("test");

        assertNotNull(responseEntity);
        assertNull(responseEntity.getBody());
        assertEquals(HttpStatusCode.valueOf(400), responseEntity.getStatusCode());
        assertEquals(HttpStatus.NOT_FOUND.getReasonPhrase(), responseEntity.getHeaders().get("error-message").get(0));
    }

    @Test
    public void getCompanyByName_failure_500(){
        when(companyService.getCompanyByName(anyString())).thenThrow(new NullPointerException());

        ResponseEntity<Company> responseEntity = companyControllerImpl.getCompanyByName("test");

        assertNotNull(responseEntity);
        assertNull(responseEntity.getBody());
        assertEquals(HttpStatusCode.valueOf(500), responseEntity.getStatusCode());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), responseEntity.getHeaders().get("error-message").get(0));
    }

    @Test
    public void addClientToCompany_success_200(){

        when(companyService.addClientToCompany(anyInt(), anyInt())).thenReturn(true);

        ResponseEntity<HttpStatus> responseEntity = companyControllerImpl.addClientToCompany(1, 1);

        assertNotNull(responseEntity);
        assertEquals(HttpStatusCode.valueOf(200), responseEntity.getStatusCode());
    }

    @Test
    public void addClientToCompany_failure_400(){
        when(companyService.addClientToCompany(anyInt(), anyInt())).thenReturn(false);

        ResponseEntity<HttpStatus> responseEntity = companyControllerImpl.addClientToCompany(1, 1);

        assertNotNull(responseEntity);
        assertEquals(HttpStatusCode.valueOf(400), responseEntity.getStatusCode());
        assertEquals(HttpStatus.BAD_REQUEST.getReasonPhrase(), responseEntity.getHeaders().get("error-message").get(0));
    }

    @Test
    public void addClientToCompany_failure_500(){
        when(companyService.addClientToCompany(anyInt(), anyInt())).thenThrow(new NullPointerException());

        ResponseEntity<HttpStatus> responseEntity = companyControllerImpl.addClientToCompany(1, 1);

        assertNotNull(responseEntity);
        assertEquals(HttpStatusCode.valueOf(500), responseEntity.getStatusCode());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), responseEntity.getHeaders().get("error-message").get(0));
    }
}
