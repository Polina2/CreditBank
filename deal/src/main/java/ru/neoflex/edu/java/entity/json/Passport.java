package ru.neoflex.edu.java.entity.json;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Passport {
    private String series;
    private String number;
    private String issueBranch;
    private Date issueDate;
}
