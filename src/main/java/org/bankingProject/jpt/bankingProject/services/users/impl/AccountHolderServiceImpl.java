package org.bankingProject.jpt.bankingProject.services.users.impl;

import org.bankingProject.jpt.bankingProject.models.Users.AccountHolder;
import org.bankingProject.jpt.bankingProject.models.accounts.Account;
import org.bankingProject.jpt.bankingProject.repositories.accounts.AccountRepository;
import org.bankingProject.jpt.bankingProject.repositories.users.AccountHolderRepository;
import org.bankingProject.jpt.bankingProject.securityConfig.models.Role;
import org.bankingProject.jpt.bankingProject.securityConfig.models.User;
import org.bankingProject.jpt.bankingProject.securityConfig.repositories.RoleRepository;
import org.bankingProject.jpt.bankingProject.services.users.interfaces.AccountHolderServiceInterface;
import org.bankingProject.jpt.bankingProject.utils.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountHolderServiceImpl implements AccountHolderServiceInterface {

    @Autowired
    private AccountHolderRepository accountHolderRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AccountRepository accountRepository;


    public AccountHolder addAccountHolder(AccountHolder user){
        Role role = roleRepository.findByName("ROLE_ACCOUNT_HOLDER");
        user.getRoles().add(role);
        return accountHolderRepository.save(user);
    }


    /*public Money viewBalance(long id, User primary, User secondary) throws Exception {
        AccountHolder accountHolder = accountHolderRepository.findById(id).get();
        Account account = accountRepository.findByPrimaryOwnerOrSecondaryOwner(primary, secondary);
        //if the user logged in id is the same that the account primaryOwner
        if(!(accountHolder.equals(primary) || accountHolder.equals(secondary)) ){
            //then return the balance of the account
            throw new Exception("this account doesn't exist");
        }
        return account.getBalance();
    }*/

}
