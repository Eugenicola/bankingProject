package org.bankingProject.jpt.bankingProject.controller.accounts.impl;

import jakarta.validation.Valid;
import org.bankingProject.jpt.bankingProject.models.accounts.Account;
import org.bankingProject.jpt.bankingProject.models.accounts.Checking;
import org.bankingProject.jpt.bankingProject.models.accounts.Savings;
import org.bankingProject.jpt.bankingProject.services.accounts.interfaces.SavingsServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SavingsControllerImpl {

    @Autowired
    private SavingsServiceInterface savingsServiceInterface;

    @PostMapping("/addSavingsAccount")
    @ResponseStatus(HttpStatus.CREATED)
    public Account addNewSavingsAccount(@DateTimeFormat(pattern = "yyyy-MM-dd")@RequestBody @Valid Savings account){
        return savingsServiceInterface.createSavingsAccount(account);
    }
}
