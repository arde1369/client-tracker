package com.astroitsolutions.clienttracker.Dao;

import java.util.Optional;

import org.springframework.stereotype.Component;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import com.astroitsolutions.clienttracker.Entity.Product;
import com.astroitsolutions.clienttracker.Repository.ProductRepository;

@Component
public class ProductDao {
    private ProductRepository productRepository;

    @CircuitBreaker(name = "databaseCircuitBreaker")
    public Optional<Product> findByName(String name){
        return productRepository.findByName(name);
    }

    @CircuitBreaker(name = "databaseCircuitBreaker")
    public void deleteByName(String name){
        productRepository.deleteByName(name);
    }

    @CircuitBreaker(name = "databaseCircuitBreaker")
    public Optional<Product> findById(int id) {
        return productRepository.findById(id);
    }

    @CircuitBreaker(name = "databaseCircuitBreaker")
    public Product save(Product retrievedProduct) {
        return productRepository.save(retrievedProduct);
    }
}
