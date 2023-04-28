package org.bankingProject.jpt.bankingProject.services.users.interfaces;

import org.bankingProject.jpt.bankingProject.models.Users.AccountHolder;
import org.bankingProject.jpt.bankingProject.securityConfig.models.User;
import org.bankingProject.jpt.bankingProject.utils.Money;

import java.util.List;

public interface AccountHolderServiceInterface {

    AccountHolder addAccountHolder (AccountHolder accountHolder);

    Money viewBalance(long id);
}
