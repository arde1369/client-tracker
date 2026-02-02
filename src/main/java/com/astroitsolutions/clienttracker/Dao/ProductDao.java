package com.astroitsolutions.clienttracker.Dao;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.astroitsolutions.clienttracker.Entity.Product;
import com.astroitsolutions.clienttracker.Repository.ProductRepository;

@Component
public class ProductDao {
    private ProductRepository productRepository;

    public Optional<Product> findByName(String name){
        return productRepository.findByName(name);
    }

    public void deleteByName(String name){
        productRepository.deleteByName(name);
    }

    public Optional<Product> findById(int id) {
        return productRepository.findById(id);
    }

    public Product save(Product retrievedProduct) {
        return productRepository.save(retrievedProduct);
    }
}
