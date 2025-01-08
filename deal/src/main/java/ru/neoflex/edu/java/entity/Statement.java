package ru.neoflex.edu.java.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import ru.neoflex.edu.java.entity.enums.ApplicationStatus;
import ru.neoflex.edu.java.entity.enums.ChangeType;
import ru.neoflex.edu.java.entity.json.LoanOffer;
import ru.neoflex.edu.java.entity.json.StatusHistory;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "statement")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class Statement {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID statementId;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    @ManyToOne
    @JoinColumn(name = "credit_id")
    private Credit credit;
    @Column
    @Enumerated(EnumType.STRING)
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
    private List<StatusHistory> statusHistory;

    public void setStatus(ApplicationStatus status) {
        this.status = status;
        if (this.statusHistory == null) {
            this.statusHistory = new ArrayList<>();
        }
        this.statusHistory.add(new StatusHistory(status, Timestamp.valueOf(LocalDateTime.now()), ChangeType.AUTOMATIC));
    }
}
