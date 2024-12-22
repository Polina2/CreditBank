package ru.neoflex.edu.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.neoflex.edu.java.entity.Client;

import java.util.UUID;

public interface JpaClientRepository extends JpaRepository<Client, UUID> {
    Client save(Client entity);
}
