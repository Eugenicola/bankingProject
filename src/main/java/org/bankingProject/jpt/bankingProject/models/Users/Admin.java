package org.bankingProject.jpt.bankingProject.models.Users;

import jakarta.persistence.Entity;
import lombok.Data;
import org.bankingProject.jpt.bankingProject.securityConfig.models.User;

@Data
@Entity
public class Admin extends User {



}

/*
Admins can create new accounts. When creating a new account they can create Checking, Savings or CreditCard Accounts.
Savings
Savings accounts have a default interest rate of 0.0025
Savings accounts may be instantiated with an interest rate other than the default, with a maximum interest rate of 0.5
Savings accounts should have a default minimumBalance of 1000
Savings accounts may be instantiated with a minimum balance of less than 1000 but no lower than 100
CreditCards
CreditCard accounts have a default creditLimit of 100
CreditCards may be instantiated with a creditLimit higher than 100 but not higher than 100000
CreditCards have a default interestRate of 0.2
CreditCards may be instantiated with an interestRate less than 0.2 but not lower than 0.1
CheckingAccounts
When creating a new Checking account, if the primaryOwner is less than 24, a StudentChecking account should be created otherwise a regular Checking Account should be created.
Checking accounts should have a minimumBalance of 250 and a monthlyMaintenanceFee of 12. */