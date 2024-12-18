package ru.neoflex.edu.java.entity;

import ru.neoflex.edu.java.entity.enums.ApplicationStatus;
import ru.neoflex.edu.java.entity.json.LoanOffer;
import ru.neoflex.edu.java.entity.json.StatusHistory;

import java.sql.Timestamp;
import java.util.UUID;

public class Statement {
    private UUID statementId;
    private UUID clientId;
    private UUID creditId;
    private ApplicationStatus status;
    private Timestamp creationDate;
    private LoanOffer appliedOffer;
    private Timestamp signDate;
    private String sesCode;
    private StatusHistory statusHistory;
}
