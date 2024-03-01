package com.example.backendprojectmodule_userservice.Security.Repositories;

import java.util.Optional;

//import sample.jpa.entity.client.Client;

import com.example.backendprojectmodule_userservice.Security.Models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {
    Optional<Client> findByClientId(String clientId);

}
