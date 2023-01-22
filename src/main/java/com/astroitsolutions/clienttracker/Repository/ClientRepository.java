package com.astroitsolutions.clienttracker.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.astroitsolutions.clienttracker.Entity.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {

     public Optional<Client> findClientByFirstnameAndLastname(String firstname, String lastname);
}
