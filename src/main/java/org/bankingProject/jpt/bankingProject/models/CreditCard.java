package org.bankingProject.jpt.bankingProject.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.bankingProject.jpt.bankingProject.utils.Money;
@Data
@Entity
public class CreditCard extends Account{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Money creditLimit;
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