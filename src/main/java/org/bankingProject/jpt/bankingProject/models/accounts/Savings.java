package org.bankingProject.jpt.bankingProject.models.accounts;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bankingProject.jpt.bankingProject.utils.Money;

import java.sql.Array;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Savings extends Account {
    private String secretKey;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column (name = "savings_minimum_balance_amount")),
            @AttributeOverride(name = "currency", column = @Column (name = "savings_minimum_balance_currency"))
    })
    private Money minimumBalance;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column (name = "savings_interest_rate_amount")),
            @AttributeOverride(name = "currency", column = @Column (name = "savings_interest_rate_currency")),
    })
    private Money interestRate;
}
// Savings
//        Savings are identical to Checking accounts except that they
//        Do NOT have a monthlyMaintenanceFee
//        Do have an interestRate