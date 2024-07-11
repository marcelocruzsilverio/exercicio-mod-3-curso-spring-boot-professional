package com.marcelocruz.crudclientes.services;

import com.marcelocruz.crudclientes.dto.ClientDTO;
import com.marcelocruz.crudclientes.entities.Client;
import com.marcelocruz.crudclientes.repositories.ClientRepository;
import com.marcelocruz.crudclientes.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id) {
        Client client = repository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
        return new ClientDTO(client);
    }

    public Page<ClientDTO> findAll(Pageable pageable) {
        Page<Client> result = repository.findAll(pageable);
        return result.map(x -> new ClientDTO(x));

    }

    @Transactional
    public ClientDTO insert(ClientDTO dto) {
        Client entity = new Client();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ClientDTO(entity);

    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO dto) {
        Client entity = repository.getReferenceById(id);
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ClientDTO(entity);

    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private void copyDtoToEntity(ClientDTO dto, Client entity) {
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setBirthDate(dto.getBirthDate());
        entity.setIncome(dto.getIncome());
        entity.setChildren(dto.getChildren());
    }

}
