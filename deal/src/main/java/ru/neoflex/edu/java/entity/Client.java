package ru.neoflex.edu.java.entity;

import ru.neoflex.edu.java.entity.enums.Gender;
import ru.neoflex.edu.java.entity.enums.MaritalStatus;
import ru.neoflex.edu.java.entity.json.Employment;
import ru.neoflex.edu.java.entity.json.Passport;

import java.sql.Date;
import java.util.UUID;

public class Client {
    private UUID clientId;
    private String lastName;
    private String firstName;
    private String middleName;
    private Date birthDate;
    private String email;
    private Gender gender;
    private MaritalStatus maritalStatus;
    private Integer dependentAmount;
    private Passport passport;
    private Employment employment;
    private String accountNumber;
}
