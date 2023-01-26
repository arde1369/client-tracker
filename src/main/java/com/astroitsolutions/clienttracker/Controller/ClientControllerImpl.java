package com.astroitsolutions.clienttracker.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.astroitsolutions.clienttracker.Entity.Client;
import com.astroitsolutions.clienttracker.Entity.Review;
import com.astroitsolutions.clienttracker.Entity.Transaction;

@RestController
@RequestMapping("/api/client")
public class ClientControllerImpl implements ClientController {

    @Override
    @PostMapping()
    public Client addClient(Client client) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @GetMapping("/{id}")
    public Client retrieveClientById(@PathVariable int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @GetMapping("/{firstname}/{lastname}")
    public Client retrieveClientByFirstnameAndLastname(@PathVariable String firstname, @PathVariable String lastname) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @PostMapping("/reviews")
    public void addReviewForProductByClientId(int clientId, Review review) {
        // TODO Auto-generated method stub
        
    }

    @Override
    @GetMapping("/reviews/{id}")
    public List<Review> getReviewsAddedByClientById(@PathVariable int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @GetMapping("/reviews/{firstname}/{lastname}")
    public List<Review> getReviewsAddedByClientByFirstnameAndLastname(@PathVariable String firstname, @PathVariable String lastname) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @PostMapping("/transactions")
    public void addTransactionForClientById(int id, Transaction transaction) {
        // TODO Auto-generated method stub
        
    }

    @Override
    @GetMapping("/transactions/{id}")
    public List<Transaction> getTransactionsByClientById(@PathVariable int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @GetMapping("/transactions/{firstname}/{lastname}")
    public List<Transaction> getTransactionsAddedByClientByFirstnameAndLastname(@PathVariable String firstname, String lastname) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @PutMapping("/rating/{id}/{rating}")
    public void updateRatingForClientById(@PathVariable int id, @PathVariable int rating) {
        // TODO Auto-generated method stub
        
    }

    @Override
    @GetMapping("/rating/{firstname}/{lastname}/{rating}")
    public void updateRatingForClientByFirstnameAndLastname(@PathVariable String firstname, @PathVariable String lastname, @PathVariable int rating) {
        // TODO Auto-generated method stub
        
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public void deleteClientById(int id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    @DeleteMapping("/delete/{firstname}/{lastname}")
    public void deleteClientByFirstAndLastname(@PathVariable String firstname, @PathVariable String lastname) {
        // TODO Auto-generated method stub
        
    }
    
}
