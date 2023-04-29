package org.bankingProject.jpt.bankingProject.services.users.impl;

import jakarta.servlet.http.PushBuilder;
import org.bankingProject.jpt.bankingProject.models.TransferMoney;
import org.bankingProject.jpt.bankingProject.models.Users.AccountHolder;
import org.bankingProject.jpt.bankingProject.models.accounts.Account;
import org.bankingProject.jpt.bankingProject.repositories.accounts.AccountRepository;
import org.bankingProject.jpt.bankingProject.repositories.users.AccountHolderRepository;
import org.bankingProject.jpt.bankingProject.securityConfig.models.Role;
import org.bankingProject.jpt.bankingProject.securityConfig.repositories.RoleRepository;
import org.bankingProject.jpt.bankingProject.securityConfig.services.impl.UserService;
import org.bankingProject.jpt.bankingProject.services.accounts.impl.AccountServiceImpl;
import org.bankingProject.jpt.bankingProject.services.users.interfaces.AccountHolderServiceInterface;
import org.bankingProject.jpt.bankingProject.utils.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.security.auth.login.AccountNotFoundException;
import java.util.Collection;


@Service
public class AccountHolderServiceImpl implements AccountHolderServiceInterface {

    @Autowired
    private AccountHolderRepository accountHolderRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private AccountServiceImpl accountService;

    public AccountHolder addAccountHolder(AccountHolder accountHolder){
        Role role = roleRepository.findByName("ROLE_ACCOUNT_HOLDER");
        accountHolder.getRoles().add(role);
        AccountHolder savedUser = accountHolderRepository.save(accountHolder);
        userService.loadUserByUsername(savedUser.getUsername());
        return savedUser;
    }

    public Money viewBalance(long id) throws AccountNotFoundException{
        Account account = accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException("Account not found"));
        if(!accountOwner(account.getId())){
            throw new ResponseStatusException (HttpStatus.UNAUTHORIZED, "The user is not the owner of the account");
        }
        return account.getBalance();
    }

    public boolean accountOwner(Long id) throws AccountNotFoundException{
        Account account = accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException("Account not found"));;
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(account.getPrimaryOwner().getUsername().equals(username) || (account.getSecondaryOwner() != null && account.getSecondaryOwner().getUsername().equals(username))) {
          return true;
        } else
      return false;
    }


    /*public Money viewBalance(long id) throws AccountNotFoundException{
       Account account = accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException("Account not found"));
       String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(account.getPrimaryOwner().getUsername().equals(username) || (account.getSecondaryOwner() != null && account.getSecondaryOwner().getUsername().equals(username))) {
            //then return the balance of the account
            return account.getBalance();
        }
        throw new ResponseStatusException (HttpStatus.UNAUTHORIZED, "The user is not the owner of the account");
    }*/
    //        if(!accountHolderService.accountOwner(origin.getId())){

    /*public boolean identifyRole(Long id){
        Account account = accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException("Account not found"));;
        Collection username = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        if(account.getPrimaryOwner().getUsername().equals(username) || (account.getSecondaryOwner() != null && account.getSecondaryOwner().getUsername().equals(username))) {
            return true;
        } else if (username.)
            return false;
    }*/

}
