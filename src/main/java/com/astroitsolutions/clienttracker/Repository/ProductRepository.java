package com.astroitsolutions.clienttracker.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.astroitsolutions.clienttracker.Entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    
    public Optional<Product> findByName(String name);

    public void deleteByName(String name);
}
