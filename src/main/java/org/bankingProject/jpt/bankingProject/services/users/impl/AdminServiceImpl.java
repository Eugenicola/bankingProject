package org.bankingProject.jpt.bankingProject.services.users.impl;

import org.bankingProject.jpt.bankingProject.dtos.MoneyBalanceDTO;
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
import org.bankingProject.jpt.bankingProject.securityConfig.services.impl.UserService;
import org.bankingProject.jpt.bankingProject.services.users.interfaces.AdminServiceInterface;
import org.bankingProject.jpt.bankingProject.utils.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
    @Autowired
    private UserService userService;

    public Admin addAdminUser(Admin admin){
        Role role = roleRepository.findByName("ROLE_ADMIN");
        admin.getRoles().add(role);
        Admin savedUser = adminRepository.save(admin);
        userService.loadUserByUsername(savedUser.getUsername());
        return savedUser;
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

    public Money updateBalance(Long id, MoneyBalanceDTO amount) throws ResponseStatusException {
        Account account = accountRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found, please check the ID"));
        account.setBalance(new Money(amount.getAmount()));
        accountRepository.save(account);
        return account.getBalance();
    }

}
