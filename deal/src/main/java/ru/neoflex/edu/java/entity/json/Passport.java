package ru.neoflex.edu.java.entity.json;

import lombok.AllArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
public class Passport {
    private String series;
    private String number;
    private String issueBranch;
    private Date issueDate;
}
