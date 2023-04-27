package org.bankingProject.jpt.bankingProject.services.users.impl;

import org.bankingProject.jpt.bankingProject.models.Users.Admin;
import org.bankingProject.jpt.bankingProject.models.accounts.Account;
import org.bankingProject.jpt.bankingProject.models.accounts.CreditCard;
import org.bankingProject.jpt.bankingProject.models.accounts.Savings;
import org.bankingProject.jpt.bankingProject.repositories.accounts.AccountRepository;
import org.bankingProject.jpt.bankingProject.repositories.accounts.CreditCardRepository;
import org.bankingProject.jpt.bankingProject.repositories.accounts.SavingsRepository;
import org.bankingProject.jpt.bankingProject.repositories.users.AdminRepository;
import org.bankingProject.jpt.bankingProject.securityConfig.models.Role;
import org.bankingProject.jpt.bankingProject.securityConfig.repositories.RoleRepository;
import org.bankingProject.jpt.bankingProject.services.users.interfaces.AdminServiceInterface;
import org.bankingProject.jpt.bankingProject.utils.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminServiceInterface {

   @Autowired
   private AdminRepository adminRepository;
   @Autowired
   private RoleRepository roleRepository;
   @Autowired
   private AccountRepository accountRepository;
   @Autowired
   private SavingsRepository savingsRepository;
   @Autowired
   private CreditCardRepository creditCardRepository;

    public Admin addAdminUser(Admin user){
        Role role = roleRepository.findByName("ROLE_ADMIN");
        user.getRoles().add(role);
        return adminRepository.save(user);
    }

    public Money viewBalance(long id) {
         Account account = accountRepository.findById(id).orElseThrow(() ->
                 new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found, please check the ID"));
         if(account instanceof Savings){
             Savings savings = (Savings) account;
             savings.applyInterest();
              savingsRepository.save(savings);
         } else if (account instanceof CreditCard) {
             CreditCard cc = (CreditCard) account;
             cc.applyInterest();
             creditCardRepository.save(cc);
         }
        return account.getBalance();
    }

}
