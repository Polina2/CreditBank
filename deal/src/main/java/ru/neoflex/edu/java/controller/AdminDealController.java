package ru.neoflex.edu.java.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.edu.java.dto.ApplicationStatusDto;
import ru.neoflex.edu.java.dto.StatementEntityDto;
import ru.neoflex.edu.java.service.AdminService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AdminDealController implements AdminDealApi{
    private final AdminService adminService;

    @Override
    public StatementEntityDto getStatementById(String statementId) {
        log.info("/deal/admin/statement/{} called", statementId);
        return adminService.getStatement(statementId);
    }

    @Override
    public List<StatementEntityDto> getAllStatements() {
        log.info("deal/admin/statement called");
        return adminService.getAllStatements();
    }

    @Override
    public void updateStatementStatus(String statementId, ApplicationStatusDto applicationStatusDto) {
        log.info("/deal/admin/statement/{}/status called with {}", statementId, applicationStatusDto);
        adminService.updateStatus(statementId, applicationStatusDto);
    }
}
