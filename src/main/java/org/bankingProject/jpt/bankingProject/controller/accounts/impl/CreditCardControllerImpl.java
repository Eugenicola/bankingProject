package org.bankingProject.jpt.bankingProject.controller.accounts.impl;

import jakarta.validation.Valid;
import org.bankingProject.jpt.bankingProject.models.accounts.Account;
import org.bankingProject.jpt.bankingProject.models.accounts.CreditCard;
import org.bankingProject.jpt.bankingProject.models.accounts.Savings;
import org.bankingProject.jpt.bankingProject.services.accounts.interfaces.CreditCardServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CreditCardControllerImpl {

    @Autowired
    private CreditCardServiceInterface creditCardServiceInterface;

    @PostMapping("/addCreditCardAccount")
    @ResponseStatus(HttpStatus.CREATED)
    public Account addNewCreditCardsAccount(@DateTimeFormat(pattern = "yyyy-MM-dd")@RequestBody @Valid CreditCard account){
        return creditCardServiceInterface.createCreditCardAccount(account);
    }
}
