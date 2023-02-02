package com.astroitsolutions.clienttracker.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.astroitsolutions.clienttracker.Entity.Company;
import com.astroitsolutions.clienttracker.Service.CompanyService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/company")
@Slf4j
public class CompanyControllerImpl implements CompanyController {

    @Autowired
    private CompanyService companyService;

    @Override
    @PutMapping()
    public ResponseEntity<Company> addOrUpdateCompany(@NonNull @RequestBody Company newCompany) {
        Company addedCompany = null;
        try{
            addedCompany = companyService.addOrUpdateCompany(newCompany);
        } catch(Exception ex){
            log.error("Unexpected error occurred - ", ex);
                return ResponseEntity
                .badRequest()
                .header("error-message", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .body(null);
        }
        return ResponseEntity.ok(addedCompany);
    }

    @Override
    @GetMapping("/id")
    public ResponseEntity<Company> getCompanyById(@RequestParam int id) {
        Company retrievedCompany = null;
        try{
            retrievedCompany = companyService.getCompanyById(id);
            if(retrievedCompany == null){
                log.error("Unable to find company by id - " + id);
                return ResponseEntity
                .badRequest()
                .header("error-message", HttpStatus.NOT_FOUND.getReasonPhrase())
                .body(null);
            }
        } catch(Exception ex){
            log.error("Unexpected error occurred - ", ex);
                return ResponseEntity
                .badRequest()
                .header("error-message", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .body(null);
        }
        return ResponseEntity.ok(retrievedCompany);
    }

    @Override
    @GetMapping("/name")
    public ResponseEntity<Company> getCompanyByName(@RequestParam String name) {
        Company retrievedCompany = null;
        try{
            retrievedCompany = companyService.getCompanyByName(name);
            if(retrievedCompany == null){
                log.error("Unable to find company by name - " + name);
                return ResponseEntity
                .badRequest()
                .header("error-message", HttpStatus.NOT_FOUND.getReasonPhrase())
                .body(null);
            }
        } catch(Exception ex){
            log.error("Unexpected error occurred - ", ex);
                return ResponseEntity
                .badRequest()
                .header("error-message", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .body(null);
        }
        return ResponseEntity.ok(retrievedCompany);
    }

    @Override
    @PostMapping("/client")
    public ResponseEntity<HttpStatus> addClientToCompany(@RequestParam int clientId, @RequestParam int companyId) {
        boolean resultsOfOperation = false;
        try{
            resultsOfOperation = companyService.addClientToCompany(clientId, companyId);
            if(resultsOfOperation == false){
                log.error("Unable to perform operations. Please contact system administrator for further troubleshooting.");
                return ResponseEntity
                .badRequest()
                .header("error-message", HttpStatus.BAD_REQUEST.getReasonPhrase())
                .body(null);
            }
        } catch(Exception ex){
            log.error("Unexpected error occurred - ", ex);
                return ResponseEntity
                .badRequest()
                .header("error-message", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .body(null);
        }
        return ResponseEntity.ok(HttpStatus.OK);
    }

    // @Override
    // @DeleteMapping("/delete/id")
    // public ResponseEntity<HttpStatus> deleteCompanyById(@RequestParam int id) {
    //     companyService.deleteCompanyById(id);
    //     return ResponseEntity.ok(HttpStatus.OK);
        
    // }

    // @Override
    // @DeleteMapping("/delete/name")
    // public ResponseEntity<HttpStatus> deleteCompanyByName(@RequestParam String name) {
    //     companyService.deleteCompanyByName(name);
    //     return ResponseEntity.ok(HttpStatus.OK);
    // }
}
