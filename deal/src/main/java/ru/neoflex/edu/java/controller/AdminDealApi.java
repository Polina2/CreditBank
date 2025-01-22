package ru.neoflex.edu.java.controller;

import org.springframework.web.bind.annotation.*;
import ru.neoflex.edu.java.dto.ApplicationStatusDto;
import ru.neoflex.edu.java.dto.StatementEntityDto;

import java.util.List;

@RequestMapping("/deal/admin")
public interface AdminDealApi extends BaseDealApi{

    @GetMapping("/statement/{statementId}")
    StatementEntityDto getStatementById(@PathVariable String statementId);

    @GetMapping("/statement")
    List<StatementEntityDto> getAllStatements();

    @PutMapping("/statement/{statementId}/status")
    void updateStatementStatus(@PathVariable String statementId, @RequestBody ApplicationStatusDto applicationStatusDto);
}
