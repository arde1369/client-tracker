package com.astroitsolutions.clienttracker.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
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
import com.astroitsolutions.clienttracker.Service.ClientService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/client")
@Slf4j
public class ClientControllerImpl implements ClientController {

    @Autowired
    private ClientService clientService;

    @Override
    @PostMapping()
    public ResponseEntity<Client> addClient(@NonNull Client client) {
        Client addedClient = null;
        try{
            addedClient = clientService.addClient(client);
        } catch(Exception ex){
            log.error("Unexpected error occurred - ", ex);
                return ResponseEntity
                .badRequest()
                .header("error-message", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .body(null);
        }
        return ResponseEntity.ok(addedClient);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Client> retrieveClientById(@PathVariable int id) {
        Client retrievedClient = null;
        try{
            retrievedClient = clientService.retrieveClientById(id);
            if(retrievedClient == null){
                log.error("Unable to find client by id - " + id);
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
        return ResponseEntity.ok(retrievedClient);
    }

    @Override
    @GetMapping("/{firstname}/{lastname}")
    public ResponseEntity<Client> retrieveClientByFirstnameAndLastname(@PathVariable @NonNull String firstname, @PathVariable @NonNull String lastname) {
        Client retrievedClient = null;
        try{
            retrievedClient = clientService.retrieveClientByFirstnameAndLastname(firstname, lastname);
            if(retrievedClient == null){
                log.error("Unable to Retrieve client by firsname - " + firstname +", and lastname - " + lastname);
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
        return ResponseEntity.ok(retrievedClient);
    }

    @Override
    @PostMapping("/reviews")
    public ResponseEntity<HttpStatus> addReviewForProductByClientId(int clientId, @NonNull Review review) {
        try{
            boolean results = clientService.addReviewForProductByClientId(clientId, review);
            if(results == false){
                log.error("Unable to add review - " + review +", for client by id - " + clientId);
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
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Override
    @GetMapping("/reviews/{id}")
    public ResponseEntity<List<Review>> getReviewsAddedByClientById(@PathVariable int id) {
        
        List<Review> retrievedClientReviews = null;
        try{
            retrievedClientReviews = clientService.getReviewsAddedByClientById(id);
            if(retrievedClientReviews == null){
                log.error("Unable to find client by id - " + id);
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
        return ResponseEntity.ok(retrievedClientReviews);
    }

    @Override
    @GetMapping("/reviews/{firstname}/{lastname}")
    public ResponseEntity<List<Review>> getReviewsAddedByClientByFirstnameAndLastname(@PathVariable @NonNull String firstname, @PathVariable @NonNull String lastname) {
        List<Review> retrievedClientReviews = null;
        try{
            retrievedClientReviews = clientService.getReviewsAddedByClientByFirstnameAndLastname(firstname, lastname);
            if(retrievedClientReviews == null){
                log.error("Unable to Retrieve client by firsname - " + firstname +", and lastname - " + lastname);
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
        return ResponseEntity.ok(retrievedClientReviews);
    }

    @Override
    @PostMapping("/transactions")
    public ResponseEntity<HttpStatus> addTransactionForClientById(int clientId, @NonNull Transaction transaction) {
        try{
            boolean results = clientService.addTransactionForClientById(clientId, transaction);
            if(results == false){
                log.error("Unable to add transaction - " + transaction +", for client by id - " + clientId);
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
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Override
    @GetMapping("/transactions/{id}")
    public ResponseEntity<List<Transaction>> getTransactionsByClientById(@PathVariable int id) {
        List<Transaction> retrievedClientTransactions = null;
        try{
            retrievedClientTransactions = clientService.getTransactionsByClientById(id);
            if(retrievedClientTransactions == null){
                log.error("Unable to find client by id - " + id);
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
        return ResponseEntity.ok(retrievedClientTransactions);
    }

    @Override
    @GetMapping("/transactions/{firstname}/{lastname}")
    public ResponseEntity<List<Transaction>> getTransactionsAddedByClientByFirstnameAndLastname(@PathVariable @NonNull String firstname, @NonNull String lastname) {
        List<Transaction> retrievedClientTransactions = null;
        try{
            retrievedClientTransactions = clientService.getTransactionsAddedByClientByFirstnameAndLastname(firstname, lastname);
            if(retrievedClientTransactions == null){
                log.error("Unable to Retrieve transactions for client by firsname - " + firstname +", and lastname - " + lastname);
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
        return ResponseEntity.ok(retrievedClientTransactions);
    }

    @Override
    @PutMapping("/rating/{id}/{rating}")
    public ResponseEntity<HttpStatus> updateRatingForClientById(@PathVariable int id, @PathVariable int rating) {
        boolean results = false;
        try{
            results = clientService.updateRatingForClientById(id, rating);
        } catch(Exception ex){
            log.error("Unexpected error occurred - ", ex);
                return ResponseEntity
                .badRequest()
                .header("error-message", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .body(null);
        }
        return (results ? ResponseEntity.ok(HttpStatus.OK) : ResponseEntity
                                                                .badRequest()
                                                                .header("error-message", HttpStatus.NOT_FOUND.getReasonPhrase())
                                                                .body(null));
    }

    @Override
    @PutMapping("/rating/{firstname}/{lastname}/{rating}")
    public ResponseEntity<HttpStatus> updateRatingForClientByFirstnameAndLastname(@PathVariable String firstname, @PathVariable String lastname, @PathVariable int rating) {
        boolean results = false;
        try{
            results = clientService.updateRatingForClientByFirstnameAndLastname(firstname, lastname, rating);
        } catch(Exception ex){
            log.error("Unexpected error occurred - ", ex);
                return ResponseEntity
                .badRequest()
                .header("error-message", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .body(null);
        }
        return (results ? ResponseEntity.ok(HttpStatus.OK) : ResponseEntity
                                                                .badRequest()
                                                                .header("error-message", HttpStatus.NOT_FOUND.getReasonPhrase())
                                                                .body(null));
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteClientById(int id) {
        clientService.deleteClientById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/delete/{firstname}/{lastname}")
    public ResponseEntity<HttpStatus> deleteClientByFirstAndLastname(@PathVariable String firstname, @PathVariable String lastname) {
        clientService.deleteClientByFirstAndLastname(firstname, lastname);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
