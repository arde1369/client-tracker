package com.astroitsolutions.clienttracker.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.astroitsolutions.clienttracker.Entity.Product;

import jakarta.transaction.Transactional;

@Transactional
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    
    public Optional<Product> findByName(String name);

    public void deleteByName(String name);
}
