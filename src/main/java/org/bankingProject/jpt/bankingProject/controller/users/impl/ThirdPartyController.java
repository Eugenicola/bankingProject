package org.bankingProject.jpt.bankingProject.controller.users.impl;

import jakarta.validation.Valid;
import org.bankingProject.jpt.bankingProject.models.TransferMoney;
import org.bankingProject.jpt.bankingProject.models.Users.ThirdParty;
import org.bankingProject.jpt.bankingProject.services.accounts.interfaces.AccountServiceInterface;
import org.bankingProject.jpt.bankingProject.services.users.interfaces.ThirdPartyServiceInterface;
import org.bankingProject.jpt.bankingProject.utils.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api")
public class ThirdPartyController {

    @Autowired
    private ThirdPartyServiceInterface thirdPartyServiceinterface;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AccountServiceInterface accountServiceInterface;

    @PostMapping("/addThirdParty")
    @ResponseStatus(HttpStatus.CREATED)
    public ThirdParty addNewThirdParty(@RequestBody @Valid ThirdParty thirdParty){
        String encodedPassword = passwordEncoder.encode(thirdParty.getPassword());
        thirdParty.setPassword(encodedPassword);
        return thirdPartyServiceinterface.addThirdParty(thirdParty);
    }

   /* @PostMapping("/depositThirdParty")
    @ResponseStatus(HttpStatus.CREATED)
    public Money depositThirdParty (@RequestBody @Valid Long id, BigDecimal amount) throws AccountNotFoundException {
        return accountServiceInterface.deposit(id, amount);
    }*/
    @PostMapping("/withdrawThirdParty")
    @ResponseStatus(HttpStatus.CREATED)
    public Money withdrawThirdParty (@RequestParam String hashKey, @RequestBody @Valid Long id, BigDecimal amount) throws AccountNotFoundException {
        return accountServiceInterface.withdraw(id, amount);
    }
    @PostMapping("/depositThirdParty")
    @ResponseStatus(HttpStatus.CREATED)
    public TransferMoney thirdPartyDeposit(@RequestBody @Valid TransferMoney transferMoney, @RequestParam String hashKey) throws AccountNotFoundException {
        return accountServiceInterface.thirdPartyDeposit(transferMoney, hashKey);
    }

    @PostMapping("/depositThirdParty")
    @ResponseStatus(HttpStatus.CREATED)
    public TransferMoney thirdPartyWithdraw(@RequestBody @Valid TransferMoney transferMoney, @RequestParam String hashKey) throws AccountNotFoundException {
        return accountServiceInterface.thirdPartyWithdraw(transferMoney, hashKey);
    }

}

