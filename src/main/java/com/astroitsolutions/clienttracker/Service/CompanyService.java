package com.astroitsolutions.clienttracker.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.astroitsolutions.clienttracker.Entity.Company;
import com.astroitsolutions.clienttracker.Repository.CompanyRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CompanyService {
    
    @Autowired
    private CompanyRepository companyRepository;

    public Company addOrUpdateCompany(Company newCompany){
        log.debug("Adding/updating company: " + newCompany.toString());

        Company addedCompany = companyRepository.save(newCompany);

        return addedCompany;
    }

    public Company getCompanyById(int id){
        log.debug("Retrieving Company by ID: " + String.valueOf(id));

        Optional<Company> retrievedCompanyOptional = companyRepository.findById(id);

        if(retrievedCompanyOptional.isPresent()){
            Company retrievedCompany = retrievedCompanyOptional.get();
            log.info("Successfully retrieved client by ID: " + retrievedCompany);
            return retrievedCompany;
        } 
            
        log.debug("Unable to retrieve client by ID: " + String.valueOf(id));
        
        return null;
    }

    public Company getCompanyByName(String name){
        log.debug("Retrieving Company by name: " + name);

        Optional<Company> retrievedCompanyOptional = companyRepository.findByName(name);

        if(retrievedCompanyOptional.isPresent()){
            Company retrievedCompany = retrievedCompanyOptional.get();
            log.info("Successfully retrieved client by ID: " + retrievedCompany);
            return retrievedCompany;
        } 
            
        log.debug("Unable to retrieve client by ID: " + name);
        
        return null;
    }

    public void deleteCompanyById(int id){
        log.info("Removing client by ID: " + id);
        companyRepository.deleteById(id);
    }

    public void deleteCompanyByName(String name){
        log.info("Removing client by name: " + name);
        companyRepository.deleteByName(name);
    }
}
