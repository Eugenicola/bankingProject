package org.bankingProject.jpt.bankingProject.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class StudentChecking extends Account{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String secretKey;
    private Status status;
}

//   StudentChecking
//        Student Checking Accounts are identical to Checking Accounts except that they do NOT have:
//        A monthlyMaintenanceFee
//        A minimumBalance