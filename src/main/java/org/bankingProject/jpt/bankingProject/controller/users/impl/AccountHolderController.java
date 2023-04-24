package org.bankingProject.jpt.bankingProject.controller.users.impl;

import jakarta.validation.Valid;
import org.bankingProject.jpt.bankingProject.models.Users.AccountHolder;
import org.bankingProject.jpt.bankingProject.services.users.interfaces.AccountHolderServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api")
public class AccountHolderController {

    @Autowired
    private AccountHolderServiceInterface accountHolderServiceInterface;

    @PostMapping("/addAccountHolder")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountHolder addNewAccountHolder(
            //@RequestParam(name = "birthDate")
            //@DateTimeFormat(pattern = "yyyy-MM-dd"), it should have a date format?
            @RequestBody @Valid AccountHolder user){
        return accountHolderServiceInterface.addAccountHolder(user);
    }
}
