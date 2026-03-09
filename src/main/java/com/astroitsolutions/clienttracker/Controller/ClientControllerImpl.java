package com.astroitsolutions.clienttracker.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.astroitsolutions.clienttracker.Service.ClientService;
import com.astroitsolutions.data_services.Entity.Client;
import com.astroitsolutions.data_services.Entity.Review;
import com.astroitsolutions.data_services.Entity.Transaction;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/clienttracker/client")
@Slf4j
public class ClientControllerImpl implements ClientController {

    @Autowired
    private ClientService clientService;

    @Override
    @PostMapping()
    public ResponseEntity<Client> addClient(@NonNull @RequestBody Client client) {
        Client addedClient = null;
        try{
            addedClient = clientService.addClient(client);
        } catch(Exception ex){
            log.error("Unexpected error occurred - ", ex);
                return ResponseEntity
                .internalServerError()
                .header("error-message", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .body(null);
        }
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(addedClient);
    }

    @Override
    @GetMapping("/id")
    public ResponseEntity<Client> retrieveClientById(@RequestParam int id) {
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
                .internalServerError()
                .header("error-message", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .body(null);
        }
        return ResponseEntity.ok(retrievedClient);
    }

    @Override
    @GetMapping("/name")
    public ResponseEntity<Client> retrieveClientByFirstnameAndLastname(@RequestParam @NonNull String firstname, @RequestParam @NonNull String lastname) {
        Client retrievedClient = null;
        try{
            retrievedClient = clientService.retrieveClientByFirstnameAndLastname(firstname.toLowerCase(), lastname.toLowerCase());
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
                .internalServerError()
                .header("error-message", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .body(null);
        }
        return ResponseEntity.ok(retrievedClient);
    }

    @Override
    @PostMapping("/reviews")
    public ResponseEntity<HttpStatus> addReviewForProductByClientId(@RequestParam int clientId, @NonNull @RequestBody Review review) {
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
                .internalServerError()
                .header("error-message", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .body(null);
        }
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Override
    @GetMapping("/reviews/id")
    public ResponseEntity<List<Review>> getReviewsAddedByClientById(@RequestParam int id, @RequestParam int pageNumber, @RequestParam int pageSize) {
        
        List<Review> retrievedClientReviews = null;
        try{
            retrievedClientReviews = clientService.getReviewsAddedByClientById(id, pageSize, pageNumber);
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
                .internalServerError()
                .header("error-message", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .body(null);
        }
        return ResponseEntity.ok(retrievedClientReviews);
    }

    @Override
    @PostMapping("/transactions")
    public ResponseEntity<HttpStatus> addTransactionForClientById(int clientId, @NonNull @RequestBody Transaction transaction) {
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
                .internalServerError()
                .header("error-message", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .body(null);
        }
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Override
    @GetMapping("/transactions/id")
    public ResponseEntity<List<Transaction>> getTransactionsByClientById(@RequestParam int id, @RequestParam int pageNumber, @RequestParam int pageSize) {
        List<Transaction> retrievedClientTransactions = null;
        try{
            retrievedClientTransactions = clientService.getTransactionsByClientById(id, pageSize, pageNumber);
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
                .internalServerError()
                .header("error-message", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .body(null);
        }
        return ResponseEntity.ok(retrievedClientTransactions);
    }

    @Override
    @PutMapping("/rating/id")
    public ResponseEntity<HttpStatus> updateRatingForClientById(@RequestParam int id, @RequestParam int rating) {
        boolean results = false;
        try{
            results = clientService.updateRatingForClientById(id, rating);
        } catch(Exception ex){
            log.error("Unexpected error occurred - ", ex);
                return ResponseEntity
                .internalServerError()
                .header("error-message", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .body(null);
        }
        return (results ? ResponseEntity.ok(HttpStatus.OK) : ResponseEntity
                                                                .badRequest()
                                                                .header("error-message", HttpStatus.NOT_FOUND.getReasonPhrase())
                                                                .body(null));
    }

    @Override
    @PutMapping("/rating/name")
    public ResponseEntity<HttpStatus> updateRatingForClientByFirstnameAndLastname(@RequestParam String firstname, @RequestParam String lastname, @RequestParam int rating) {
        boolean results = false;
        try{
            results = clientService.updateRatingForClientByFirstnameAndLastname(firstname.toLowerCase(), lastname.toLowerCase(), rating);
        } catch(Exception ex){
            log.error("Unexpected error occurred - ", ex);
                return ResponseEntity
                .internalServerError()
                .header("error-message", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .body(null);
        }
        return (results ? ResponseEntity.ok(HttpStatus.OK) : ResponseEntity
                                                                .badRequest()
                                                                .header("error-message", HttpStatus.NOT_FOUND.getReasonPhrase())
                                                                .body(null));
    }

    // @Override
    // @DeleteMapping("/delete/id")
    // public ResponseEntity<HttpStatus> deleteClientById(@RequestParam int id) {
    //     clientService.deleteClientById(id);
    //     return ResponseEntity.ok(HttpStatus.OK);
    // }

    // @Override
    // @DeleteMapping("/delete/{firstname}/{lastname}")
    // public ResponseEntity<HttpStatus> deleteClientByFirstAndLastname(@PathVariable String firstname, @PathVariable String lastname) {
    //     clientService.deleteClientByFirstAndLastname(firstname, lastname);
    //     return ResponseEntity.ok(HttpStatus.OK);
    // }
}
