package com.astroitsolutions.clienttracker.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.astroitsolutions.clienttracker.Dao.ClientDao;
import com.astroitsolutions.clienttracker.Dao.ProductDao;
import com.astroitsolutions.clienttracker.Dao.ReviewDao;
import com.astroitsolutions.clienttracker.Dao.TransactionDao;
import com.astroitsolutions.clienttracker.Entity.Client;
import com.astroitsolutions.clienttracker.Entity.Product;
import com.astroitsolutions.clienttracker.Entity.Review;
import com.astroitsolutions.clienttracker.Entity.Transaction;
import com.astroitsolutions.clienttracker.Util.RatingCalculator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ClientService {
    
    @Autowired
    private ClientDao clientDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ReviewDao reviewDao;

    @Autowired
    private TransactionDao transactionDao;
    
    public Client addClient(Client client){

        log.debug("Adding client: " + client.toString());

        Client addedClient = clientDao.save(client);
        log.info("Successfully added client: " + addedClient);
        
        return addedClient;
    }

    public Client retrieveClientById(int id){
        log.debug("Retrieving client by ID: " + String.valueOf(id));

        Optional<Client> retrievedClientOptional = clientDao.findById(id);

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

        Optional<Client> retrievedClientOptional = clientDao.findByFirstnameAndLastname(firstname, lastname);

        if(retrievedClientOptional.isPresent()){
            Client retrievedClient = retrievedClientOptional.get();
            log.info("Successfully retrieved client by firsname - " + firstname +", and lastname - " + lastname);
            return retrievedClient;
        }

        return null;
    }

    public boolean addReviewForProductByClientId(int clientId, Review review){
        log.debug("Adding review by client id - " + clientId);

        Optional<Client> retrievedClientOptional = clientDao.findById(clientId);
        boolean results = false;
        if(retrievedClientOptional.isPresent()){
            Client retrievedClient = retrievedClientOptional.get();
            review.setClient(retrievedClient);

            retrievedClient.getReviews().add(review);

            updateProductRatingFromReview(review);

            clientDao.save(retrievedClient);
            log.info("Successfully added review for client ID " + clientId);
            results = true;
        } else {
            log.debug("Unable to add review for product. No client found by by ID: " + clientId + ". Review was not added...");
        }
        return results;
    }

    //Helper method to update product rating from review
    private void updateProductRatingFromReview(Review review) {
        Optional<Product> retrievedProductOptional = productDao.findById(review.getProduct().getId());
        Product retrievedProduct = retrievedProductOptional.get();

        int calculatedRating = RatingCalculator.calculate(review.getRating(), retrievedProduct.getRating(), retrievedProduct.getNumberOfRatings());
        retrievedProduct.setRating(calculatedRating);
        retrievedProduct.getProductReviews().add(review);

        productDao.save(retrievedProduct);
    }

    public List<Review> getReviewsAddedByClientById(int id, int pageSize, int pageNumber){
        log.debug("Retrieving reviews by client id - " + id);

        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Optional<List<Review>> retrievedReviewListOptional = reviewDao.findAllByClientId(id, pageable);
        if(retrievedReviewListOptional.isPresent()){
            List<Review> retreivedReviewsList = retrievedReviewListOptional.get();

            log.info("Successfully retrieved reviews for client ID " + id);

            return retreivedReviewsList;
        } else {
            log.info("Unable to retrieve reviews. No client found by client by ID: " + id );
        }
        return null;
    }

    public boolean addTransactionForClientById(int id, Transaction transaction){
        log.debug("Adding transaction" + transaction + " for client id - " + id);
        Optional<Client> retrievedClientOptional = clientDao.findById(id);
        boolean results = false;
        if(retrievedClientOptional.isPresent()){
            Client retrievedClient = retrievedClientOptional.get();
            log.info("Successfully retrieved transactions for client ID " + id);

            retrievedClient.getTransactions().add(transaction);
            transaction.setClient(retrievedClient);

            clientDao.save(retrievedClient);
            results = true;
            log.debug("Successfully Added transaction" + transaction + " for client id - " + id);
        } else {
            log.info("Unable to retrieve transactions for client. No client found by ID: " + id );
        }
        return results;
    }

    public boolean addTransactionForClientByFirstnameAndLastname(String firstname, String lastname, Transaction transaction){
        log.debug("Adding transaction " + transaction + " by firsname - " + firstname +", and lastname - " + lastname);

        Optional<Client> retrievedClientOptional = clientDao.findByFirstnameAndLastname(firstname, lastname);
        boolean results = false;
        if(retrievedClientOptional.isPresent()){
            Client retrievedClient = retrievedClientOptional.get();
            retrievedClient.getTransactions().add(transaction);
            transaction.setClient(retrievedClient);

            clientDao.save(retrievedClient);
            results = true;
            log.info("Successfully Added transaction for client by firsname - " + firstname +", and lastname - " + lastname);
        } else {
            log.info("Unable to Add transaction. No client found by firsname - " + firstname +", and lastname - " + lastname);
        }
        return results;
    }

    public List<Transaction> getTransactionsByClientById(int id, int pageSize, int pageNumber){
        log.debug("Retrieving transactions by client id - " + id);

        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Optional<List<Transaction>> retrievedTransactionListOptional = transactionDao.findAllByClientId(id, pageable);
        if(retrievedTransactionListOptional.isPresent()){
            List<Transaction> retrievedClient = retrievedTransactionListOptional.get();

            log.info("Successfully retrieved transactions for client ID " + id);

            return retrievedClient;
        } else {
            log.info("Unable to retrieve transactions. No client found by client by ID: " + id );
        }
        return null;
    }

    public boolean updateRatingForClientById(int id, int rating){
        log.debug("Updating rating for client by client id - " + id);

        Optional<Client> retrievedClientOptional = clientDao.findById(id);
        boolean results = false;
        if(retrievedClientOptional.isPresent()){
            Client retrievedClient = retrievedClientOptional.get();

            int calculatedRating = RatingCalculator.calculate(rating, retrievedClient.getRating(), retrievedClient.getNumberOfRatings());

            retrievedClient.setRating(calculatedRating);

            clientDao.save(retrievedClient);
            
            results = true;
            
            log.info("Successfully updated client rating for client ID " + id);
        } else {
            log.info("Unable to update client rating. No client found by client by ID: " + id );
        }
        return results;
    }

    public boolean updateRatingForClientByFirstnameAndLastname(String firstname, String lastname, int rating){
        log.debug("Updating rating for client by firsname - " + firstname +", and lastname - " + lastname);
        Optional<Client> retrievedClientOptional = clientDao.findByFirstnameAndLastname(firstname, lastname);
        boolean results = false;
        if(retrievedClientOptional.isPresent()){
            Client retrievedClient = retrievedClientOptional.get();

            int calculatedRatingEnum = RatingCalculator.calculate(rating, retrievedClient.getRating(), retrievedClient.getNumberOfRatings() );

            retrievedClient.setRating(calculatedRatingEnum);

            clientDao.save(retrievedClient);

            results = true;

            log.info("Successfully updated client rating for client by firsname - " + firstname +", and lastname - " + lastname);
        } else {
            log.info("Unable to update client rating. No client found by firsname - " + firstname +", and lastname - " + lastname);
        }
        return results;
    }

    // public void deleteClientById(int id){
    //     log.info("Removing client by ID: " + id);
    //     clientDao.deleteById(id);
    // }

    // public void deleteClientByFirstAndLastname(String firstname, String lastname){
    //     log.info("Removing client by firsname - " + firstname +", and lastname - " + lastname);
    //     clientDao.deleteByFirstnameAndLastname(firstname, lastname);
    // }
}
