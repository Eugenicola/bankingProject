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
public class CreditCard extends Account {
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "credit_limit_amount")),
            @AttributeOverride(name = "currency", column = @Column (name = "credit_limit_currency"))
    })
    private Money creditLimit;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "credit_interest_rate_amount")),
            @AttributeOverride(name = "currency", column = @Column (name = "credit_interest_rate_currency"))
    })
    private Money interestRate;


}
//CreditCard
//        CreditCard Accounts have:
//        A balance
//        A PrimaryOwner
//        An optional SecondaryOwner
//        A creditLimit
//        An interestRate
//        A penaltyFee