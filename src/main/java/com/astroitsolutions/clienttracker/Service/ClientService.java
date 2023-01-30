package com.astroitsolutions.clienttracker.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.astroitsolutions.clienttracker.Entity.Client;
import com.astroitsolutions.clienttracker.Entity.Product;
import com.astroitsolutions.clienttracker.Entity.Review;
import com.astroitsolutions.clienttracker.Entity.Transaction;
import com.astroitsolutions.clienttracker.Repository.ClientRepository;
import com.astroitsolutions.clienttracker.Repository.ProductRepository;
import com.astroitsolutions.clienttracker.Util.RatingCalculator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ClientService {
    
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductRepository productRepository;
    
    public Client addClient(Client client){

        log.debug("Adding client: " + client.toString());

        Client addedClient = clientRepository.save(client);
        log.info("Successfully added client: " + addedClient);
        
        return addedClient;
    }

    public Client retrieveClientById(int id){
        log.debug("Retrieving client by ID: " + String.valueOf(id));

        Optional<Client> retrievedClientOptional = clientRepository.findById(id);

        if(retrievedClientOptional.isPresent()){
            Client retrievedClient = retrievedClientOptional.get();
            log.info("Successfully retrieved client by ID: " + retrievedClient);
            return retrievedClient;
        } 
            
        log.debug("Unable to retrieve client by ID: " + String.valueOf(id));
        
        return null;
    }

    public Client retrieveClientByFirstnameAndLastname(String firstname, String lastname){
        log.debug("Retrieving client by firsname - " + firstname +", and lastname - " + lastname);

        Optional<Client> retrievedClientOptional = clientRepository.findByFirstnameAndLastname(firstname, lastname);

        if(retrievedClientOptional.isPresent()){
            Client retrievedClient = retrievedClientOptional.get();
            log.info("Successfully retrieved client by firsname - " + firstname +", and lastname - " + lastname);
            return retrievedClient;
        }

        return null;
    }

    public boolean addReviewForProductByClientId(int clientId, Review review){
        log.debug("Adding review by client id - " + clientId);

        Optional<Client> retrievedClientOptional = clientRepository.findById(clientId);
        boolean results = false;
        if(retrievedClientOptional.isPresent()){
            Client retrievedClient = retrievedClientOptional.get();

            retrievedClient.getReviews().add(review);

            updateProductRatingFromReview(review.getProduct(), review.getRating());

            clientRepository.save(retrievedClient);
            log.info("Successfully added review for client ID " + clientId);
            results = true;
        } else {
            log.debug("Unable to add review for product. No client found by by ID: " + clientId + ". Review was not added...");
        }
        return results;
    }

    //Helper method to update product rating from review
    private void updateProductRatingFromReview(Product product, int rating) {
        Optional<Product> retrievedProductOptional = productRepository.findById(product.getId());
        Product retrievedProduct = retrievedProductOptional.get();

        int calculatedRating = RatingCalculator.calculate(rating, retrievedProduct.getRating());
        retrievedProduct.setRating(calculatedRating);

        productRepository.save(retrievedProduct);
    }

    public List<Review> getReviewsAddedByClientById(int id){
        log.debug("Retrieving reviews by client id - " + id);

        Optional<Client> retrievedClientOptional = clientRepository.findById(id);
        if(retrievedClientOptional.isPresent()){
            Client retrievedClient = retrievedClientOptional.get();

            log.info("Successfully retrieved reviews for client ID " + id);

            return retrievedClient.getReviews();
        } else {
            log.info("Unable to retrieve reviews. No client found by client by ID: " + id );
        }
        return null;
    }

    public List<Review> getReviewsAddedByClientByFirstnameAndLastname(String firstname, String lastname){
        log.debug("Retrieving reviews for client by firsname - " + firstname +", and lastname - " + lastname);

        Optional<Client> retrievedClientOptional = clientRepository.findByFirstnameAndLastname(firstname, lastname);

        if(retrievedClientOptional.isPresent()){
            Client retrievedClient = retrievedClientOptional.get();
            log.info("Successfully retrieved reviews for client by firsname - " + firstname +", and lastname - " + lastname);
            return retrievedClient.getReviews();
        } else {
            log.info("Unable to retrieve reviews. No client found by firsname - " + firstname +", and lastname - " + lastname);
        }
        return null;
    }

    public boolean addTransactionForClientById(int id, Transaction transaction){
        log.debug("Adding transaction" + transaction + " for client id - " + id);
        Optional<Client> retrievedClientOptional = clientRepository.findById(id);
        boolean results = false;
        if(retrievedClientOptional.isPresent()){
            Client retrievedClient = retrievedClientOptional.get();
            log.info("Successfully retrieved transactions for client ID " + id);

            retrievedClient.getTransactions().add(transaction);

            clientRepository.save(retrievedClient);
            results = true;
            log.debug("Successfully Added transaction" + transaction + " for client id - " + id);
        } else {
            log.info("Unable to retrieve transactions for client. No client found by ID: " + id );
        }
        return results;
    }

    public void addTransactionForClientByFirstnameAndLastname(String firstname, String lastname, Transaction transaction){
        log.debug("Adding transaction " + transaction + " by firsname - " + firstname +", and lastname - " + lastname);

        Optional<Client> retrievedClientOptional = clientRepository.findByFirstnameAndLastname(firstname, lastname);

        if(retrievedClientOptional.isPresent()){
            Client retrievedClient = retrievedClientOptional.get();
            retrievedClient.getTransactions().add(transaction);

            clientRepository.save(retrievedClient);
            log.info("Successfully Added transaction for client by firsname - " + firstname +", and lastname - " + lastname);
        } else {
            log.info("Unable to Add transaction. No client found by firsname - " + firstname +", and lastname - " + lastname);
        }
    }

    public List<Transaction> getTransactionsByClientById(int id){
        log.debug("Retrieving transactions by client id - " + id);

        Optional<Client> retrievedClientOptional = clientRepository.findById(id);
        if(retrievedClientOptional.isPresent()){
            Client retrievedClient = retrievedClientOptional.get();

            log.info("Successfully retrieved transactions for client ID " + id);

            return retrievedClient.getTransactions();
        } else {
            log.info("Unable to retrieve transactions. No client found by client by ID: " + id );
        }
        return null;
    }

    public List<Transaction> getTransactionsAddedByClientByFirstnameAndLastname(String firstname, String lastname){
        log.debug("Retrieving transactions for client by firsname - " + firstname +", and lastname - " + lastname);

        Optional<Client> retrievedClientOptional = clientRepository.findByFirstnameAndLastname(firstname, lastname);

        if(retrievedClientOptional.isPresent()){
            Client retrievedClient = retrievedClientOptional.get();
            log.info("Successfully retrieved transactions for client by firsname - " + firstname +", and lastname - " + lastname);
            return retrievedClient.getTransactions();
        } else {
            log.info("Unable to retrieve transactions. No client found by firsname - " + firstname +", and lastname - " + lastname);
        }
        return null;
    }

    public boolean updateRatingForClientById(int id, int rating){
        log.debug("Updating rating for client by client id - " + id);

        Optional<Client> retrievedClientOptional = clientRepository.findById(id);
        boolean results = false;
        if(retrievedClientOptional.isPresent()){
            Client retrievedClient = retrievedClientOptional.get();

            int calculatedRating = RatingCalculator.calculate(rating, retrievedClient.getRating());

            retrievedClient.setRating(calculatedRating);

            clientRepository.save(retrievedClient);
            
            results = true;
            
            log.info("Successfully updated client rating for client ID " + id);
        } else {
            log.info("Unable to update client rating. No client found by client by ID: " + id );
        }
        return results;
    }

    public boolean updateRatingForClientByFirstnameAndLastname(String firstname, String lastname, int rating){
        log.debug("Updating rating for client by firsname - " + firstname +", and lastname - " + lastname);
        Optional<Client> retrievedClientOptional = clientRepository.findByFirstnameAndLastname(firstname, lastname);
        boolean results = false;
        if(retrievedClientOptional.isPresent()){
            Client retrievedClient = retrievedClientOptional.get();

            int calculatedRatingEnum = RatingCalculator.calculate(rating, retrievedClient.getRating());

            retrievedClient.setRating(calculatedRatingEnum);

            clientRepository.save(retrievedClient);

            results = true;

            log.info("Successfully updated client rating for client by firsname - " + firstname +", and lastname - " + lastname);
        } else {
            log.info("Unable to update client rating. No client found by firsname - " + firstname +", and lastname - " + lastname);
        }
        return results;
    }

    public void deleteClientById(int id){
        log.info("Removing client by ID: " + id);
        clientRepository.deleteById(id);
    }

    public void deleteClientByFirstAndLastname(String firstname, String lastname){
        log.info("Removing client by firsname - " + firstname +", and lastname - " + lastname);
        clientRepository.deleteByFirstnameAndLastname(firstname, lastname);
    }
}
