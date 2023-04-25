package org.bankingProject.jpt.bankingProject.services.accounts.interfaces;

import org.bankingProject.jpt.bankingProject.models.accounts.Account;

public interface AccountServiceInterface {

    Account deleteAccount (long id, Account account);
}
