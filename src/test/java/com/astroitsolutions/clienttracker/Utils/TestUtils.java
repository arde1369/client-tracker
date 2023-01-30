package com.astroitsolutions.clienttracker.Utils;

import java.util.ArrayList;
import java.util.List;

import com.astroitsolutions.clienttracker.Entity.Address;
import com.astroitsolutions.clienttracker.Entity.Client;
import com.astroitsolutions.clienttracker.Entity.Company;
import com.astroitsolutions.clienttracker.Entity.CompanySize;
import com.astroitsolutions.clienttracker.Entity.Product;
import com.astroitsolutions.clienttracker.Entity.Review;
import com.astroitsolutions.clienttracker.Entity.Transaction;

public class TestUtils {

    public Client createNewCompleteClient(){
        Client mockClient = new Client();

        mockClient.setId(1);
        mockClient.setFirstname("Arash");
        mockClient.setLastname("Dehdari");
        mockClient.setCompany(createNewCompany(mockClient));
        mockClient.setAddress(createNewAddress());
        mockClient.setRating(5);
        mockClient.setTransactions(createTransactionList(mockClient));

        return mockClient;
    }

    /**
     * Helper method to create list of transactions
     */
    public List<Transaction> createTransactionList(Client mockClient) {
        List<Transaction> transactions = new ArrayList<>();

        Transaction t1 = new Transaction();
        t1.setClient(mockClient);
        t1.setId(1);
        t1.setProducts(createProductList(t1, mockClient));
        t1.setTotalPrice(200d);

        Transaction t2 = new Transaction();
        t2.setClient(mockClient);
        t2.setId(2);
        t2.setProducts(createProductList(t2, mockClient));
        t2.setTotalPrice(100d);

        transactions.add(t1);
        transactions.add(t2);

        return transactions;
    }

    public Transaction createSingleTransaction(Client mockClient) {
        Transaction t1 = new Transaction();
        t1.setClient(mockClient);
        t1.setId(10);
        t1.setProducts(createProductList(t1, mockClient));
        t1.setTotalPrice(200d);

        return t1;
    }

    /**
    * Helper method to create list of products
    */
    public List<Product> createProductList(Transaction t1, Client mockClient) {
        List<Product> products = new ArrayList<>();

        Product p1 = new Product();
        p1.setDescription(null);
        p1.setId(1);
        p1.setName("Apple Juice");
        p1.setPrice(3.99);
        p1.setRating(4);
        p1.setTransaction(t1);
        p1.setProductReviews(createReviewsList(p1, mockClient));

        Product p2 = new Product();
        p2.setDescription(null);
        p2.setId(2);
        p2.setName("Apple Juice");
        p2.setPrice(3.99);
        p2.setRating(4);
        p2.setTransaction(t1);
        p2.setProductReviews(createReviewsList(p2, mockClient));

        products.add(p1);
        products.add(p2);

        return products;
    }

    /**
     * Helper method to create list of reviews
     */
    public List<Review> createReviewsList(Product product, Client mockClient) {
        List<Review> reviews = new ArrayList<>();

        Review r1 = new Review();
        r1.setClient(mockClient);
        r1.setProduct(product);
        r1.setDescription("Great product!");
        r1.setRating(5);
        r1.setId(1);

        Review r2 = new Review();
        r2.setClient(mockClient);
        r2.setProduct(product);
        r2.setDescription("Good product!");
        r2.setRating(4);
        r2.setId(2);

        mockClient.getReviews().add(r1);
        mockClient.getReviews().add(r2);

        reviews.add(r1);
        reviews.add(r2);

        return reviews;
    }

    /**
     * Helper method to create an address
     */
    public Address createNewAddress() {
        Address address = new Address();
        address.setId(1);
        address.setStreet("1234 Azoth st.");
        address.setAptOrSte(null);
        address.setCity("Atlantis");    
        address.setState("Pacific");
        address.setZip("91823");

        return address;
    }

    /**
     * Helper method to create a company
     */
    public Company createNewCompany(Client mockClient) {
        Company company = new Company();
        List<Client> listOfEmployees = new ArrayList<>();
        listOfEmployees.add(mockClient);

        company.setCompanySize(CompanySize.M);
        company.setId(1);
        company.setInternational(false);
        company.setName("Astro It Solutions LLC");
        company.setDiscountPercentage(5.0);
        company.setEmployeesWhoAreClients(listOfEmployees);
        company.setHeadquartersAddress(createNewAddress());      

        return company;
    }
}
