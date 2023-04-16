package org.bankingProject.jpt.bankingProject.models.Accounts;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bankingProject.jpt.bankingProject.utils.Money;

@Data
@Entity
public class Savings extends Account{
    private String secretKey;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Embedded
    private Money interestRate;
}
// Savings
//        Savings are identical to Checking accounts except that they
//        Do NOT have a monthlyMaintenanceFee
//        Do have an interestRate