package com.marcelocruz.crudclientes.services;

import com.marcelocruz.crudclientes.dto.ClientDTO;
import com.marcelocruz.crudclientes.entities.Client;
import com.marcelocruz.crudclientes.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id) {
        Client client = repository.findById(id).get();
        return new ClientDTO(client);
    }
}
