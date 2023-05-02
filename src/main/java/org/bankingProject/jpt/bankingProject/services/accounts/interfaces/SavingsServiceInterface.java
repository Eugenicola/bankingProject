package org.bankingProject.jpt.bankingProject.services.accounts.interfaces;

import org.bankingProject.jpt.bankingProject.models.accounts.Account;
import org.bankingProject.jpt.bankingProject.models.accounts.Savings;

public interface SavingsServiceInterface {

    Account createSavingsAccount(Savings account);
}
