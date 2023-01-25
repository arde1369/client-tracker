package com.astroitsolutions.clienttracker.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.astroitsolutions.clienttracker.Entity.Product;
import com.astroitsolutions.clienttracker.Repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    public Product addProduct(Product product){
        log.debug("Adding product: " + product.toString());

        Product addedProduct = null;

        if(product != null){
            try{
                addedProduct = productRepository.save(product);
                log.info("Successfully added client: " + addedProduct);
            } catch(Exception ex){
                log.error("Error while adding client - ", ex);
                throw ex;
            }
        } else {
            log.error("Unable to add null client");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return addedProduct;
    }

    public Product findProductById(int id){
        log.debug("Retrieving product by ID: " + String.valueOf(id));

        Optional<Product> retrievedProductOptional = productRepository.findById(id);

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

        Optional<Product> retrievedProductOptional = productRepository.findByName(name);

        if(retrievedProductOptional.isPresent()){
            Product retrievedProduct = retrievedProductOptional.get();
            log.info("Successfully retrieved product by name: " + retrievedProduct);
            return retrievedProduct;
        }
        
        log.debug("Unable to retrieve product by name: " + name);
        
        return null;
    }

    public void deleteProductById(int id){
        log.debug("Deleting product by id: " + id);
        productRepository.deleteById(id);
    }

    public void deleteProductByName(String name){
        log.debug("Deleting product by name: " + name);
        productRepository.deleteByName(name);
    }
}