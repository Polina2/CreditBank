package ru.neoflex.edu.java.entity.json;

import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;

@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class PaymentScheduleElement {
    private Integer number;
    private Date date;
    private BigDecimal totalPayment;
    private BigDecimal interestPayment;
    private BigDecimal debtPayment;
    private BigDecimal remainingDebt;
}
