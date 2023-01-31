package com.astroitsolutions.clienttracker.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.astroitsolutions.clienttracker.Entity.Company;

import jakarta.transaction.Transactional;

@Transactional
@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
    
    public void deleteByName(String name);

    public Optional<Company> findByName(String name);
}
