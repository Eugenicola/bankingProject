package org.bankingProject.jpt.bankingProject.services.users.interfaces;

import org.bankingProject.jpt.bankingProject.models.Users.AccountHolder;
import org.bankingProject.jpt.bankingProject.securityConfig.models.User;
import org.bankingProject.jpt.bankingProject.utils.Money;

import java.util.List;

public interface AccountHolderServiceInterface {

    AccountHolder addAccountHolder (AccountHolder user);

    //List<Money> (long id, User primary, User secondary) throws Exception;
}
