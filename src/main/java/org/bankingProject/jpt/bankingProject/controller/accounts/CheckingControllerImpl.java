package org.bankingProject.jpt.bankingProject.controller.accounts;

import jakarta.validation.Valid;
import org.bankingProject.jpt.bankingProject.models.accounts.Account;
import org.bankingProject.jpt.bankingProject.models.accounts.Checking;
import org.bankingProject.jpt.bankingProject.services.accounts.interfaces.CheckingServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CheckingControllerImpl {

    @Autowired
    private CheckingServiceInterface checkingServiceInterface;

    @PostMapping("/addCheckingAccount")
    @ResponseStatus(HttpStatus.CREATED)
    public Account addNewCheckingAccount(@DateTimeFormat(pattern = "yyyy-MM-dd")@RequestBody @Valid Checking account){
        return checkingServiceInterface.createCheckingAccount(account);
    }
}
