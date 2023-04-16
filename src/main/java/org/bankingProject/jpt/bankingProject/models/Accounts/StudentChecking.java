package org.bankingProject.jpt.bankingProject.models.Accounts;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
@Entity
public class StudentChecking extends Account{
    private String secretKey;
    @Enumerated(EnumType.STRING)
    private Status status;
}

//   StudentChecking
//        Student Checking Accounts are identical to Checking Accounts except that they do NOT have:
//        A monthlyMaintenanceFee
//        A minimumBalance