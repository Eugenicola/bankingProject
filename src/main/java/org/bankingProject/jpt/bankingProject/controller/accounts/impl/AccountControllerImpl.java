package org.bankingProject.jpt.bankingProject.controller.accounts.impl;

import jakarta.validation.Valid;
import org.bankingProject.jpt.bankingProject.models.Users.Admin;
import org.bankingProject.jpt.bankingProject.models.accounts.Account;
import org.bankingProject.jpt.bankingProject.services.accounts.interfaces.AccountServiceInterface;
import org.bankingProject.jpt.bankingProject.services.users.interfaces.AdminServiceInterface;
import org.bankingProject.jpt.bankingProject.utils.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AccountControllerImpl {

    @Autowired
    private AccountServiceInterface accountServiceInterface;

    @Autowired
    private AdminServiceInterface adminServiceInterface;

    @GetMapping("/adminBalance/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Money checkBalance(@PathVariable("id") long id) {
        return adminServiceInterface.viewBalance(id);
    }

    @DeleteMapping("/deleteAccount/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Account deleteAccount(@PathVariable("id") long id, Account account) {
        return accountServiceInterface.deleteAccount(id, account);
    }
}
