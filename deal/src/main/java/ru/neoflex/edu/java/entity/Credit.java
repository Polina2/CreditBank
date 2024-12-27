package ru.neoflex.edu.java.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import ru.neoflex.edu.java.entity.enums.CreditStatus;
import ru.neoflex.edu.java.entity.json.PaymentScheduleElement;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "credit")
@NoArgsConstructor
@Setter
@ToString
@EqualsAndHashCode
public class Credit {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID creditId;
    @Column
    private BigDecimal amount;
    @Column
    private Integer term;
    @Column
    private BigDecimal monthlyPayment;
    @Column
    private BigDecimal rate;
    @Column
    private BigDecimal psk;
    @JdbcTypeCode(SqlTypes.JSON)
    private List<PaymentScheduleElement> paymentSchedule;
    @Column
    private Boolean insuranceEnabled;
    @Column
    private Boolean salaryClient;
    @Column
    private CreditStatus creditStatus;
}
