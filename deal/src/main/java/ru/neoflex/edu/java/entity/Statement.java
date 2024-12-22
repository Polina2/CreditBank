package ru.neoflex.edu.java.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import ru.neoflex.edu.java.entity.enums.ApplicationStatus;
import ru.neoflex.edu.java.entity.json.LoanOffer;
import ru.neoflex.edu.java.entity.json.StatusHistory;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "statement")
@AllArgsConstructor
@Getter
@ToString
public class Statement {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID statementId;
    @ManyToOne
    private Client client;
    @ManyToOne
    private Credit credit;
    @Column
    private ApplicationStatus status;
    @Column
    private Timestamp creationDate;
    @JdbcTypeCode(SqlTypes.JSON)
    private LoanOffer appliedOffer;
    @Column
    private Timestamp signDate;
    @Column
    private String sesCode;
    @JdbcTypeCode(SqlTypes.JSON)
    private StatusHistory statusHistory;
}
