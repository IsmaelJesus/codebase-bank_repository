package com.codecon.bank_project.Repository;

import com.codecon.bank_project.Entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID>{

    public Optional<Client> findById(UUID id);

    public Optional<Client> findByCpf(String cpf);
}
