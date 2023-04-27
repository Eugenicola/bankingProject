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

import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountControllerImpl {

    @Autowired
    private AccountServiceInterface accountServiceInterface;

    @DeleteMapping("/deleteAccount/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Account deleteAccount(@PathVariable("id") long id, Account account) {
        return accountServiceInterface.deleteAccount(id, account);
    }
}
