package org.bankingProject.jpt.bankingProject.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.bankingProject.jpt.bankingProject.utils.Money;

@Data
@Entity
public class Savings extends Account{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String secretKey;
    private Status status;
    private Money interestRate;
}
// Savings
//        Savings are identical to Checking accounts except that they
//        Do NOT have a monthlyMaintenanceFee
//        Do have an interestRate