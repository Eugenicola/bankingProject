package org.bankingProject.jpt.bankingProject.models.Accounts;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Data;
import org.bankingProject.jpt.bankingProject.utils.Money;
@Data
@Entity
public class CreditCard extends Account{
    @Embedded
    private Money creditLimit;
    @Embedded
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