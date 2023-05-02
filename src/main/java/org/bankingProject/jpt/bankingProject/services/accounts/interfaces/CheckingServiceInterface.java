package org.bankingProject.jpt.bankingProject.services.accounts.interfaces;

import org.bankingProject.jpt.bankingProject.models.accounts.Account;
import org.bankingProject.jpt.bankingProject.models.accounts.Checking;

public interface CheckingServiceInterface {

    Account createCheckingAccount (Checking account);

    void checkingToStudentChecking (Checking account);
}
