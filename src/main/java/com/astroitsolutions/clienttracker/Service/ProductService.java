package com.astroitsolutions.clienttracker.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.astroitsolutions.clienttracker.Dao.ProductDao;
import com.astroitsolutions.clienttracker.Dao.ReviewDao;
import com.astroitsolutions.clienttracker.Entity.Product;
import com.astroitsolutions.clienttracker.Entity.Review;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class ProductService {
    
    @Autowired
    private ProductDao productDao;

    @Autowired
    private ReviewDao reviewDao;

    public Product addProduct(Product product){
        log.debug("Adding product: " + product.toString());

        Product addedProduct = productDao.save(product);
        log.info("Successfully added client: " + addedProduct);

        return addedProduct;
    }

    public Product findProductById(int id){
        log.debug("Retrieving product by ID: " + String.valueOf(id));

        Optional<Product> retrievedProductOptional = productDao.findById(id);

        if(retrievedProductOptional.isPresent()){
            Product retrievedProduct = retrievedProductOptional.get();
            log.info("Successfully retrieved product by ID: " + retrievedProduct);
            return retrievedProduct;
        }
        
        log.debug("Unable to retrieve product by ID: " + String.valueOf(id));
        
        return null;
    }

    public Product findProductByName(String name){
        log.debug("Retrieving product by name: " + name);

        Optional<Product> retrievedProductOptional = productDao.findByName(name);

        if(retrievedProductOptional.isPresent()){
            Product retrievedProduct = retrievedProductOptional.get();
            log.info("Successfully retrieved product by name: " + retrievedProduct);
            return retrievedProduct;
        }
        
        log.debug("Unable to retrieve product by name: " + name);
        
        return null;
    }

    public List<Review> retrieveAllReviewsForProductById(int id, int pageSize, int pageNumber){
        log.debug("Retrieving reviews for product by ID: " + String.valueOf(id));

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Optional<List<Review>> reviewsListOptional= reviewDao.findAllByProductId(id, pageable);

        if(reviewsListOptional.isPresent()){
            List<Review> reviewsList = reviewsListOptional.get();
            log.debug("Successfully retrieved reviews for product by ID: " + reviewsList);
            return reviewsList;
        }
        
        log.debug("Unable to retrieve reviews for product by ID: " + String.valueOf(id));
        
        return null;
    }

    public Product activateProductById(int productId){
        log.debug("Activating product by id: " + productId);

        Optional<Product> retrievedProductOptional = productDao.findById(productId);
        Product retrievedProduct = null;

        if(retrievedProductOptional.isPresent()){
            retrievedProduct = retrievedProductOptional.get();

            retrievedProduct.setActive(true);

            log.info("Successfully activated product by id: " + productId);
            return retrievedProduct;
        }
        
        log.debug("Unable to activate product by id: " + productId);
        
        return retrievedProduct;
    }

    public Product deactivateProductById(int productId){
        log.debug("Deactivating product by id: " + productId);

        Optional<Product> retrievedProductOptional = productDao.findById(productId);
        Product retrievedProduct = null;

        if(retrievedProductOptional.isPresent()){
            retrievedProduct = retrievedProductOptional.get();

            retrievedProduct.setActive(false);

            log.info("Successfully deactivated product by id: " + productId);
            return retrievedProduct;
        }
        
        log.debug("Unable to deactivate product by id: " + productId);
        
        return retrievedProduct;
    }

    public Product activateProductByName(String name){
        log.debug("Activating product by name: " + name);

        Optional<Product> retrievedProductOptional = productDao.findByName(name);
        Product retrievedProduct = null;

        if(retrievedProductOptional.isPresent()){
            retrievedProduct = retrievedProductOptional.get();

            retrievedProduct.setActive(true);

            log.info("Successfully activated product by id: " + name);
            return retrievedProduct;
        }
        
        log.debug("Unable to activate product by id: " + name);
        
        return retrievedProduct;
    }

    public Product deactivateProductByName(String name){
        log.debug("Deactivating product by name: " + name);

        Optional<Product> retrievedProductOptional = productDao.findByName(name);
        Product retrievedProduct = null;
        if(retrievedProductOptional.isPresent()){
            retrievedProduct = retrievedProductOptional.get();

            retrievedProduct.setActive(false);

            log.info("Successfully deactivated product by name: " + name);
            return retrievedProduct;
        }
        
        log.debug("Unable to deactivate product by name: " + name);
        
        return retrievedProduct;
    }

    // public void deleteProductById(int id){
    //     log.debug("Deleting product by id: " + id);
    //     productDao.deleteById(id);
    // }

    // public void deleteProductByName(String name){
    //     log.debug("Deleting product by name: " + name);
    //     productDao.deleteByName(name);
    // }
}
