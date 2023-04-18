package org.bankingProject.jpt.bankingProject.models.accounts;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bankingProject.jpt.bankingProject.utils.Money;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Checking extends Account {
    private String secretKey;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column (name = "checking_minimum_balance_amount")),
            @AttributeOverride(name = "currency", column = @Column (name = "checking_minimum_balance_currency"))
    })
    private Money minimumBalance;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column (name = "checking_monthly_maintenance_amount")),
            @AttributeOverride(name = "currency", column = @Column (name = "checking_monthly_maintenance_currency"))
    })
    private Money monthlyMaintenanceFee;
    @Enumerated(EnumType.STRING)
    private Status status;
}
