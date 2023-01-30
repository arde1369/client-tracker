package com.astroitsolutions.clienttracker.Service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.astroitsolutions.clienttracker.Entity.Company;
import com.astroitsolutions.clienttracker.Entity.CompanySize;
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

    TestUtils clientTestUtils = new TestUtils();
    
    @Test
    public void addCompany_success(){
        Company mockCompany = clientTestUtils.createNewMedSizeCompany(null);

        Mockito.when(companyRepository.save(mockCompany)).thenReturn(mockCompany);

        Company addedCompany = companyService.addOrUpdateCompany(mockCompany);

        assertNotNull(addedCompany);
        assertEquals(addedCompany, mockCompany);
    }

    @Test
    public void updateCompany_success(){
        Company mockCompany = clientTestUtils.createNewMedSizeCompany(null);

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
}
