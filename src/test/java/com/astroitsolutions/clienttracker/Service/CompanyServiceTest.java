package com.astroitsolutions.clienttracker.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;

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
import com.astroitsolutions.clienttracker.Entity.Company;
import com.astroitsolutions.clienttracker.Entity.CompanySize;
import com.astroitsolutions.clienttracker.Repository.ClientRepository;
import com.astroitsolutions.clienttracker.Repository.CompanyRepository;
import com.astroitsolutions.clienttracker.Utils.TestUtils;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CompanyServiceTest {

    @Autowired
    @InjectMocks
    private CompanyService companyService;

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private ClientRepository clientRepository;

    TestUtils testUtils = new TestUtils();
    
    @Test
    public void addCompany_success(){
        Company mockCompany = testUtils.createNewMedSizeCompany(null);

        Mockito.when(companyRepository.save(mockCompany)).thenReturn(mockCompany);

        Company addedCompany = companyService.addOrUpdateCompany(mockCompany);

        assertNotNull(addedCompany);
        assertEquals(addedCompany, mockCompany);
    }

    @Test
    public void updateCompany_success(){
        Company mockCompany = testUtils.createNewMedSizeCompany(null);

        Mockito.when(companyRepository.save(mockCompany)).thenReturn(mockCompany);

        Company addedCompany = companyService.addOrUpdateCompany(mockCompany);

        assertNotNull(addedCompany);
        assertEquals(addedCompany, mockCompany);
        assertEquals(mockCompany.getCompanySize(), addedCompany.getCompanySize());

        mockCompany.setCompanySize(CompanySize.L);

        Mockito.when(companyRepository.save(mockCompany)).thenReturn(mockCompany);

        addedCompany = companyService.addOrUpdateCompany(mockCompany);

        assertNotNull(addedCompany);
        assertEquals(addedCompany, mockCompany);
        assertEquals(CompanySize.L, addedCompany.getCompanySize());
    }

    @Test
    public void getCompanyById_success(){
        Company mockCompany = testUtils.createNewMedSizeCompany(null);
        Optional<Company> companyOptional = Optional.of(mockCompany);

        Mockito.when(companyRepository.findById(mockCompany.getId())).thenReturn(companyOptional);

        Company retreivedCompany = companyService.getCompanyById(mockCompany.getId());

        assertNotNull(retreivedCompany);
        assertEquals(retreivedCompany, mockCompany);
    }

    @Test
    public void getCompanyByName_success(){
        Company mockCompany = testUtils.createNewMedSizeCompany(null);
        Optional<Company> companyOptional = Optional.of(mockCompany);

        Mockito.when(companyRepository.findByName(mockCompany.getName())).thenReturn(companyOptional);

        Company retreivedCompany = companyService.getCompanyByName(mockCompany.getName());

        assertNotNull(retreivedCompany);
        assertEquals(retreivedCompany, mockCompany);
    }

    @Test
    public void getCompanyById_null_noCompanyFoundByID(){
        Company mockCompany = testUtils.createNewMedSizeCompany(null);
        Optional<Company> companyOptional = Optional.empty();

        Mockito.when(companyRepository.findById(mockCompany.getId())).thenReturn(companyOptional);

        Company retreivedCompany = companyService.getCompanyById(mockCompany.getId());

        assertNull(retreivedCompany);
    }

    @Test
    public void getCompanyByName_null_noCompanyFoundByName(){
        Company mockCompany = testUtils.createNewMedSizeCompany(null);
        Optional<Company> companyOptional = Optional.empty();

        Mockito.when(companyRepository.findByName(mockCompany.getName())).thenReturn(companyOptional);

        Company retreivedCompany = companyService.getCompanyByName(mockCompany.getName());

        assertNull(retreivedCompany);
    }

    @Test 
    public void addClientToCompany_success(){
        Company mockCompany = testUtils.createNewMedSizeCompany(null);
        Optional<Company> companyOptional = Optional.of(mockCompany);

        Client mockClient = testUtils.createNewCompleteClient();
        Optional<Client> clientOptional = Optional.of(mockClient);

        Mockito.when(companyRepository.findById(anyInt())).thenReturn(companyOptional);
        Mockito.when(clientRepository.findById(anyInt())).thenReturn(clientOptional);

        assertTrue(companyService.addClientToCompany(0, 0));
    }

    @Test 
    public void addClientToCompany_clientNotPresent(){
        Mockito.when(clientRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertFalse(companyService.addClientToCompany(0, 0));
    }

    @Test 
    public void addClientToCompany_companyNotPresent(){
        Client mockClient = testUtils.createNewCompleteClient();
        Optional<Client> clientOptional = Optional.of(mockClient);

        Mockito.when(clientRepository.findById(anyInt())).thenReturn(clientOptional);
        Mockito.when(companyRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertFalse(companyService.addClientToCompany(0, 0));
    }
}
