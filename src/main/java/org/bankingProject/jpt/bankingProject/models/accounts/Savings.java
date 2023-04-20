package org.bankingProject.jpt.bankingProject.models.accounts;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bankingProject.jpt.bankingProject.utils.Money;

import java.math.BigDecimal;
import java.sql.Array;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Savings extends Account {
    private String secretKey;

    @DecimalMin(value="100", message = "Minimum balance cannot be less than 100")
    private BigDecimal minimumBalance = new BigDecimal(1000);
    @DecimalMax(value = "0.5", message = "Interest rate cannot be higher than 0.5")
    private BigDecimal interestRate = new BigDecimal(0.0025);

    @Enumerated(EnumType.STRING)
    private Status status;


}
// Savings
//        Savings are identical to Checking accounts except that they
//        Do NOT have a monthlyMaintenanceFee
//        Do have an interestRate