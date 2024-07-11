package com.marcelocruz.crudclientes.repositories;

import com.marcelocruz.crudclientes.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
