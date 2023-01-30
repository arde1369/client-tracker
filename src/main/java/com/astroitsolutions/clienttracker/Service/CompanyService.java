package com.astroitsolutions.clienttracker.Service;

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

    public void deleteCompanyById(int id){
        log.info("Removing client by ID: " + id);
        companyRepository.deleteById(id);
    }

    public void deleteCompanyByName(String name){
        log.info("Removing client by name: " + name);
        companyRepository.deleteByName(name);
    }
}
