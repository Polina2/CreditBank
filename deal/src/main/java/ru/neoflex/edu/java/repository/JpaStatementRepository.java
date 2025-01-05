package ru.neoflex.edu.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.neoflex.edu.java.entity.Statement;

import java.util.Optional;
import java.util.UUID;

public interface JpaStatementRepository extends JpaRepository<Statement, UUID> {
}
