package com.astroitsolutions.clienttracker.Controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.astroitsolutions.clienttracker.Entity.Company;

@RestController
@RequestMapping("/api/company")
public class CompanyControllerImpl implements CompanyController {

    @Override
    @PutMapping()
    public Company addOrUpdateCompany(Company newCompany) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public void deleteCompanyById(@PathVariable int id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    @DeleteMapping("/delete/{name}")
    public void deleteCompanyByName(@PathVariable String name) {
        // TODO Auto-generated method stub
        
    }
    
}
