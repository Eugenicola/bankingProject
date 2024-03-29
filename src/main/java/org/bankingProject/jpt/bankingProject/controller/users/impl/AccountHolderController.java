package org.bankingProject.jpt.bankingProject.controller.users.impl;

import jakarta.validation.Valid;
import org.bankingProject.jpt.bankingProject.models.TransferMoney;
import org.bankingProject.jpt.bankingProject.models.Users.AccountHolder;
import org.bankingProject.jpt.bankingProject.services.accounts.interfaces.AccountServiceInterface;
import org.bankingProject.jpt.bankingProject.services.users.interfaces.AccountHolderServiceInterface;
import org.bankingProject.jpt.bankingProject.utils.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;


@RestController
@RequestMapping("/api")
public class AccountHolderController {

    @Autowired
    private AccountHolderServiceInterface accountHolderServiceInterface;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AccountServiceInterface accountServiceInterface;

    @PostMapping("/addAccountHolder")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountHolder addNewAccountHolder(@RequestBody @Valid AccountHolder accountHolder){
        String encodedPassword = passwordEncoder.encode(accountHolder.getPassword());
        accountHolder.setPassword(encodedPassword);
        return accountHolderServiceInterface.addAccountHolder(accountHolder);
    }

    @GetMapping("/balance/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Money checkBalance(@PathVariable("id") long id) throws AccountNotFoundException  {
        return accountHolderServiceInterface.checkBalance(id);
    }

    /*@GetMapping("/balance/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Money> checkBalanceList(@PathVariable("id") long id) throws AccountNotFoundException  {
        return accountHolderServiceInterface.checkBalance(id);
    }*/

    @PostMapping("/transfer")
    @ResponseStatus(HttpStatus.CREATED)
    public TransferMoney accHolderTransference(@RequestBody @Valid TransferMoney transferMoney)throws AccountNotFoundException{
        return accountServiceInterface.transferMoney(transferMoney);
    }
}

