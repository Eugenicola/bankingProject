package org.bankingProject.jpt.bankingProject.models.accounts;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CreditCard extends Account {

    @DecimalMax(value="100.000", message = "Interest credit limit cannot be higher than 100000")
    private BigDecimal creditLimit = new BigDecimal(100);
    @DecimalMin(value="0.1", message = "Interest rate cannot be lower than 0.1")
    private BigDecimal interestRate =  new BigDecimal(0.2);


}
//CreditCard
//        CreditCard Accounts have:
//        A balance
//        A PrimaryOwner
//        An optional SecondaryOwner
//        A creditLimit
//        An interestRate
//        A penaltyFee