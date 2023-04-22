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
import java.time.Duration;
import java.time.LocalDate;
import java.util.Date;

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

    private LocalDate lastInterestDate;

    public void applyInterest() {
        LocalDate currentDate = LocalDate.now();
        if (lastInterestDate == null || lastInterestDate.plusDays(365).isBefore(currentDate)) {
            BigDecimal interest = getBalance().getAmount().multiply(interestRate);
            setBalance(new Money(getBalance().getAmount().add(interest)));
            lastInterestDate = (currentDate);
        } else {
            Duration timeDiff = Duration.between(lastInterestDate.atStartOfDay(), currentDate.atStartOfDay());
            System.out.println("Interest was last added " + timeDiff.toDays() + " days ago.");
        }
    }
}



//Interest on savings accounts is added to the account annually at the rate of specified interestRate per year.
// That means that if I have 1000000 in a savings account with a 0.01 interest rate, 1% of 1 Million is added to my account after 1 year.
// When a savings account balance is accessed, you must determine if it has been 1 year or more since either the account was created
// or since interest was added to the account, and add the appropriate interest to the balance if necessary.

