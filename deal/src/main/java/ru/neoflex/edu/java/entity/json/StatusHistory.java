package ru.neoflex.edu.java.entity.json;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import ru.neoflex.edu.java.entity.enums.ApplicationStatus;
import ru.neoflex.edu.java.entity.enums.ChangeType;

import java.sql.Timestamp;

@AllArgsConstructor
@Getter
@ToString
public class StatusHistory {
    private ApplicationStatus status;
    private Timestamp time;
    private ChangeType changeType;
}
