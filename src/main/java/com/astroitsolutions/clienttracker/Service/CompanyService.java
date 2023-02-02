package com.astroitsolutions.clienttracker.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.astroitsolutions.clienttracker.Entity.Client;
import com.astroitsolutions.clienttracker.Entity.Company;
import com.astroitsolutions.clienttracker.Repository.ClientRepository;
import com.astroitsolutions.clienttracker.Repository.CompanyRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CompanyService {
    
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ClientRepository clientRepository;

    public Company addOrUpdateCompany(Company newCompany){
        log.debug("Adding/updating company: " + newCompany.toString());

        Company addedCompany = companyRepository.save(newCompany);

        return addedCompany;
    }

    public boolean addClientToCompany(int clientId, int companyId){
        boolean resultOfOperation = false;
        
        Optional<Client> clientOptional = clientRepository.findById(clientId);
        Optional<Company> companyOptional = companyRepository.findById(companyId);

        if(!clientOptional.isPresent()){
            log.info("Unable to find client by the ID provided - ", clientId);
            return resultOfOperation;
        }

        if(!companyOptional.isPresent()){
            log.info("Unable to find company by the ID provided - ", companyId);
            return resultOfOperation;
        }

        Client clientToUpdate = clientOptional.get();
        Company companyToUpdate = companyOptional.get();

        clientToUpdate.setCompany(companyToUpdate);
        companyToUpdate.getEmployeesWhoAreClients().add(clientToUpdate);

        clientRepository.save(clientToUpdate);
        companyRepository.save(companyToUpdate);

        resultOfOperation = true;

        return resultOfOperation;
    }

    public Company getCompanyById(int id){
        log.debug("Retrieving Company by ID: " + String.valueOf(id));

        Optional<Company> retrievedCompanyOptional = companyRepository.findById(id);

        if(retrievedCompanyOptional.isPresent()){
            Company retrievedCompany = retrievedCompanyOptional.get();
            log.info("Successfully retrieved Company by ID: " + retrievedCompany);
            return retrievedCompany;
        } 
            
        log.debug("Unable to retrieve Company by ID: " + String.valueOf(id));
        
        return null;
    }

    public Company getCompanyByName(String name){
        log.debug("Retrieving Company by name: " + name);

        Optional<Company> retrievedCompanyOptional = companyRepository.findByName(name);

        if(retrievedCompanyOptional.isPresent()){
            Company retrievedCompany = retrievedCompanyOptional.get();
            log.info("Successfully retrieved Company by ID: " + retrievedCompany);
            return retrievedCompany;
        } 
            
        log.debug("Unable to retrieve Company by ID: " + name);
        
        return null;
    }

    // public void deleteCompanyById(int id){
    //     log.info("Removing Company by ID: " + id);
    //     companyRepository.deleteById(id);
    // }

    // public void deleteCompanyByName(String name){
    //     log.info("Removing Company by name: " + name);
    //     companyRepository.deleteByName(name);
    // }
}
