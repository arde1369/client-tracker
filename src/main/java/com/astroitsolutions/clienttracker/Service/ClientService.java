package com.astroitsolutions.clienttracker.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.astroitsolutions.clienttracker.Entity.Client;
import com.astroitsolutions.clienttracker.Entity.Rating;
import com.astroitsolutions.clienttracker.Entity.Review;
import com.astroitsolutions.clienttracker.Entity.Transaction;
import com.astroitsolutions.clienttracker.Repository.ClientRepository;
import com.astroitsolutions.clienttracker.Util.RatingCalculator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ClientService {
    
    @Autowired
    public ClientRepository clientRepository;

    public Client addClient(Client client){

        log.debug("Adding client: " + client.toString());

        Client addedClient = null;

        try{
            addedClient = clientRepository.save(client);
            log.info("Successfully added client: " + addedClient);
        } catch(Exception ex){
            log.error("Error while adding client - ", ex);
            throw ex;
        }
        return addedClient;
    }

    public Client retrieveClientById(int id){
        log.debug("Retrieving client by ID: " + String.valueOf(id));

        Optional<Client> retrievedClientOptional = clientRepository.findById(id);

        if(retrievedClientOptional.isPresent()){
            Client retrievedClient = retrievedClientOptional.get();
            log.info("Successfully retrieved client by ID: " + retrievedClient);
            return retrievedClient;
        } else {
            log.debug("Unable to retrieve client by ID: " + String.valueOf(id));

        }
        return null;
    }

    public Client retrieveClientByFirstnameAndLastname(String firstname, String lastname){
        log.debug("Retrieving client by firsname - " + firstname +", and lastname - " + lastname);

        Optional<Client> retrievedClientOptional = clientRepository.findClientByFirstnameAndLastname(firstname, lastname);

        if(retrievedClientOptional.isPresent()){
            Client retrievedClient = retrievedClientOptional.get();
            log.info("Successfully retrieved client by firsname - " + firstname +", and lastname - " + lastname);
            return retrievedClient;
        } else {
            log.debug("Unable to retrieve client by firsname - " + firstname +", and lastname - " + lastname);
        }
        return null;
    }

    public void addReviewForProductByClientId(int clientId, Review review){
        log.debug("Adding review by client id: - " + clientId);

        Optional<Client> retrievedClientOptional = clientRepository.findById(clientId);
        if(retrievedClientOptional.isPresent()){
            Client retrievedClient = retrievedClientOptional.get();
            retrievedClient.getReviews().add(review);

            clientRepository.save(retrievedClient);
            log.info("Successfully added review for client ID " + clientId);
        } else {
            log.debug("Unable to add review for product. No client found by by ID: " + clientId + ". Review was not added...");
        }
    }

    public List<Review> getReviewsAddedByClientById(int id){
        log.debug("Retrieving reviews by client id: - " + id);

        Optional<Client> retrievedClientOptional = clientRepository.findById(id);
        if(retrievedClientOptional.isPresent()){
            Client retrievedClient = retrievedClientOptional.get();

            log.info("Successfully retrieved reviews for client ID " + id);

            return retrievedClient.getReviews();
        } else {
            log.debug("Unable to retrieve reviews. No client found by client by ID: " + id );
        }
        return null;
    }

    public List<Review> getReviewsAddedByClientByFirstnameAndLastname(String firstname, String lastname){
        log.debug("Retrieving reviews for client by firsname - " + firstname +", and lastname - " + lastname);

        Optional<Client> retrievedClientOptional = clientRepository.findClientByFirstnameAndLastname(firstname, lastname);

        if(retrievedClientOptional.isPresent()){
            Client retrievedClient = retrievedClientOptional.get();
            log.info("Successfully retrieved reviews for client by firsname - " + firstname +", and lastname - " + lastname);
            return retrievedClient.getReviews();
        } else {
            log.debug("Unable to retrieve reviews. No client found by firsname - " + firstname +", and lastname - " + lastname);
        }
        return null;
    }

    public List<Transaction> getTransactionsByClientById(int id){
        log.debug("Retrieving transactions by client id: - " + id);

        Optional<Client> retrievedClientOptional = clientRepository.findById(id);
        if(retrievedClientOptional.isPresent()){
            Client retrievedClient = retrievedClientOptional.get();

            log.info("Successfully retrieved transactions for client ID " + id);

            return retrievedClient.getTransactions();
        } else {
            log.debug("Unable to retrieve transactions. No client found by client by ID: " + id );
        }
        return null;
    }

    public List<Transaction> getTransactionsAddedByClientByFirstnameAndLastname(String firstname, String lastname){
        log.debug("Retrieving transactions for client by firsname - " + firstname +", and lastname - " + lastname);

        Optional<Client> retrievedClientOptional = clientRepository.findClientByFirstnameAndLastname(firstname, lastname);

        if(retrievedClientOptional.isPresent()){
            Client retrievedClient = retrievedClientOptional.get();
            log.info("Successfully retrieved transactions for client by firsname - " + firstname +", and lastname - " + lastname);
            return retrievedClient.getTransactions();
        } else {
            log.debug("Unable to retrieve transactions. No client found by firsname - " + firstname +", and lastname - " + lastname);
        }
        return null;
    }

    public void updateRatingForClientById(int id, String rating){
        log.debug("Updating rating for client by client id: - " + id);

        Optional<Client> retrievedClientOptional = clientRepository.findById(id);
        if(retrievedClientOptional.isPresent()){
            Client retrievedClient = retrievedClientOptional.get();

            Rating calculatedRatingEnum = RatingCalculator.calculate(rating, retrievedClient.getRating());

            retrievedClient.setRating(calculatedRatingEnum);

            clientRepository.save(retrievedClient);

            log.info("Successfully updated client rating for client ID " + id);
        } else {
            log.debug("Unable to update client rating. No client found by client by ID: " + id );
        }
    }

    public void updateRatingForClientByFirstnameAndLastname(String firstname, String lastname, String rating){
        log.debug("Updating rating for client by firsname - " + firstname +", and lastname - " + lastname);
        Optional<Client> retrievedClientOptional = clientRepository.findClientByFirstnameAndLastname(firstname, lastname);
        if(retrievedClientOptional.isPresent()){
            Client retrievedClient = retrievedClientOptional.get();

            Rating calculatedRatingEnum = RatingCalculator.calculate(rating, retrievedClient.getRating());

            retrievedClient.setRating(calculatedRatingEnum);

            clientRepository.save(retrievedClient);

            log.info("Successfully updated client rating for client by firsname - " + firstname +", and lastname - " + lastname);
        } else {
            log.debug("Unable to update client rating. No client found by firsname - " + firstname +", and lastname - " + lastname);
        }
    }
}
