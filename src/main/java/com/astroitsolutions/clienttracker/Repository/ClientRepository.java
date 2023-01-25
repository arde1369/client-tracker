package com.astroitsolutions.clienttracker.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.astroitsolutions.clienttracker.Entity.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {

     public Optional<Client> findByFirstnameAndLastname(String firstname, String lastname);

     public void deleteByFirstnameAndLastname(String firstname, String lastname);
}
