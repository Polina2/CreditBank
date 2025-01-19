package ru.neoflex.edu.java.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.neoflex.edu.java.dto.ApplicationStatusDto;
import ru.neoflex.edu.java.dto.StatementEntityDto;
import ru.neoflex.edu.java.entity.Statement;
import ru.neoflex.edu.java.mapper.EntityDtoMapper;
import ru.neoflex.edu.java.repository.JpaStatementRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminService {
    private final JpaStatementRepository statementRepository;
    private final EntityDtoMapper mapper;

    public StatementEntityDto getStatement(String statementId) {
        Statement statement = statementRepository.findById(UUID.fromString(statementId)).orElseThrow();
        return mapper.toStatementEntityDto(statement);
    }

    public List<StatementEntityDto> getAllStatements() {
        List<Statement> statements = statementRepository.findAll();
        return statements.stream().map(mapper::toStatementEntityDto).toList();
    }

    public void updateStatus(String statementId, ApplicationStatusDto statusDto) {
        Statement statement = statementRepository.findById(UUID.fromString(statementId)).orElseThrow();
        statement.setStatus(statusDto.status());
        statement = statementRepository.save(statement);
        log.info("Saved statement {}", statement);
    }
}
