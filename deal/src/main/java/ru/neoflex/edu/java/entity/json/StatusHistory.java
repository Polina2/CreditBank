package ru.neoflex.edu.java.entity.json;

import ru.neoflex.edu.java.entity.enums.ChangeType;

import java.sql.Timestamp;

public class StatusHistory {
    private String status;
    private Timestamp time;
    private ChangeType changeType;
}
