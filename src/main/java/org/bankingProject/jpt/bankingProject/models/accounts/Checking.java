package org.bankingProject.jpt.bankingProject.models.accounts;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bankingProject.jpt.bankingProject.utils.Money;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Checking extends Account {
    private String secretKey;
    private BigDecimal minimumBalance = new BigDecimal(250);
    private BigDecimal monthlyMaintenanceFee = new BigDecimal(12);
    @Enumerated(EnumType.STRING)
    private Status status;

}
