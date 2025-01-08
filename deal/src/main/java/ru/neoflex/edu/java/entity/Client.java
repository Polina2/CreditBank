package ru.neoflex.edu.java.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import ru.neoflex.edu.java.entity.enums.Gender;
import ru.neoflex.edu.java.entity.enums.MaritalStatus;
import ru.neoflex.edu.java.entity.json.Employment;
import ru.neoflex.edu.java.entity.json.Passport;

import java.sql.Date;
import java.util.UUID;

@Entity
@Table(name = "client")
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Client {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID clientId;
    @Column
    private String lastName;
    @Column
    private String firstName;
    @Column
    private String middleName;
    @Column
    private Date birthDate;
    @Column
    private String email;
    @Column
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column
    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;
    @Column
    private Integer dependentAmount;
    @JdbcTypeCode(SqlTypes.JSON)
    private Passport passport;
    @JdbcTypeCode(SqlTypes.JSON)
    private Employment employment;
    @Column
    private String accountNumber;
}
