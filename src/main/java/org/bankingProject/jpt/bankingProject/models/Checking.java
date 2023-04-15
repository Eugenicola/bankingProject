package org.bankingProject.jpt.bankingProject.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.bankingProject.jpt.bankingProject.utils.Money;

@Data
@Entity
public class Checking extends Account{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String secretKey;
    private Money minimumBalance;
    private Money monthlyMaintenanceFee;
    private Status status;
}
