package ru.neoflex.edu.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.neoflex.edu.java.entity.Credit;

import java.util.UUID;

public interface JpaCreditRepository extends JpaRepository<Credit, UUID> {
    @Override
    Credit save(Credit entity);
}
