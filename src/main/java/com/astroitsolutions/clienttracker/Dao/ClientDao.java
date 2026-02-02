package com.astroitsolutions.clienttracker.Dao;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.astroitsolutions.clienttracker.Entity.Client;
import com.astroitsolutions.clienttracker.Repository.ClientRepository;

@Component
public class ClientDao {

    private ClientRepository clientRepository;

    public Optional<Client> findByFirstnameAndLastname(String firstname, String lastname){
        return clientRepository.findByFirstnameAndLastname(firstname, lastname);
    }

     public void deleteByFirstnameAndLastname(String firstname, String lastname){
        clientRepository.deleteByFirstnameAndLastname(firstname, lastname);
     }

     public Optional<Client> findById(int id) {
        return clientRepository.findById(id);
     }

     public Client save(Client client) {
        return clientRepository.save(client);
     }
}
