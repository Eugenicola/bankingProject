package org.bankingProject.jpt.bankingProject.models.Accounts;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import org.bankingProject.jpt.bankingProject.utils.Money;

@Data
@Entity
public class Checking extends Account{
    private String secretKey;
    @Embedded
    private Money minimumBalance;
    @Embedded
    private Money monthlyMaintenanceFee;
    @Enumerated(EnumType.STRING)
    private Status status;
}
