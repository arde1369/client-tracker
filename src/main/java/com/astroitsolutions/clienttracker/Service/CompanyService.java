package com.astroitsolutions.clienttracker.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

        Company addedCompany = null;

        if(newCompany != null){
            try{
                addedCompany = companyRepository.save(newCompany);
                log.info("Successfully added/updated company: " + addedCompany);
            } catch(Exception ex){
                log.error("Error while Adding/updating - ", ex);
                throw ex;
            }
        } else {
            log.error("Unable to Add/update null client");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return addedCompany;
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
