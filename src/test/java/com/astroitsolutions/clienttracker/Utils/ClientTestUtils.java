package com.astroitsolutions.clienttracker.Utils;

import java.util.List;

import com.astroitsolutions.clienttracker.Entity.Address;
import com.astroitsolutions.clienttracker.Entity.Client;
import com.astroitsolutions.clienttracker.Entity.Company;
import com.astroitsolutions.clienttracker.Entity.Review;
import com.astroitsolutions.clienttracker.Entity.Transaction;

public class ClientTestUtils {

    public Client createNewCompleteClient(){
        Client mockClient = new Client();

        mockClient.setId(1);
        mockClient.setFirstname("Arash");
        mockClient.setLastname("Dehdari");
        mockClient.setCompany(createNewCompany());
        mockClient.setAddress(createNewAddress());
        mockClient.setRating(5);
        mockClient.setReviews(createReviewsList());
        mockClient.setTransactions(createTransactionList());

        return mockClient;
    }

    private List<Transaction> createTransactionList() {
        return null;
    }

    private List<Review> createReviewsList() {
        return null;
    }

    private Address createNewAddress() {
        return null;
    }

    private Company createNewCompany() {
        return null;
    }
}
