package ru.neoflex.edu.java.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.neoflex.edu.java.dto.LoanOfferDto;
import ru.neoflex.edu.java.dto.LoanStatementRequestDto;

import java.util.List;

@RequestMapping("/statement")
public interface StatementApi {
    @PostMapping
    List<LoanOfferDto> createStatement(@RequestBody @Valid LoanStatementRequestDto request);

    @PostMapping("/offer")
    void selectOffer(@RequestBody @Valid LoanOfferDto request);
}
