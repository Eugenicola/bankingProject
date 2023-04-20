package org.bankingProject.jpt.bankingProject.models.accounts;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bankingProject.jpt.bankingProject.securityConfig.models.User;
import org.bankingProject.jpt.bankingProject.utils.Money;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;
@Data
@NoArgsConstructor
@Entity
public class Account {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column (name = "account_balance_amount")),
            @AttributeOverride(name = "currency", column = @Column (name = "account_balance_currency"))
    })
    private Money balance;
    @ManyToOne
    @JoinColumn(name="user_account")
    private User primaryOwner;
    @ManyToOne
    @JoinColumn(name="user_account_secondary")
    private User secondaryOwner;
    private BigDecimal penaltyFee = new BigDecimal(40) ;
    private Date creationDate;

    public void applyPenalty(BigDecimal minimumBalance){
        if(balance.getAmount().compareTo(minimumBalance)<0){
            setBalance(new Money(balance.getAmount().subtract(penaltyFee)));
        }

    }

}

 /* Checking
Checking Accounts should have:
A balance
A secretKey
A PrimaryOwner
An optional SecondaryOwner
A minimumBalance
A penaltyFee
A monthlyMaintenanceFee
A creationDate
A status (FROZEN, ACTIVE)

StudentChecking
Student Checking Accounts are identical to Checking Accounts except that they do NOT have:
A monthlyMaintenanceFee
A minimumBalance

Savings
Savings are identical to Checking accounts except that they
Do NOT have a monthlyMaintenanceFee
Do have an interestRate

CreditCard
CreditCard Accounts have:
A balance
A PrimaryOwner
An optional SecondaryOwner
A creditLimit
An interestRate
A penaltyFee*/
