package org.bankingProject.jpt.bankingProject.controller.users.impl;

import jakarta.validation.Valid;
import org.bankingProject.jpt.bankingProject.dtos.MoneyBalanceDTO;
import org.bankingProject.jpt.bankingProject.models.Users.Admin;
import org.bankingProject.jpt.bankingProject.models.accounts.Account;
import org.bankingProject.jpt.bankingProject.services.accounts.interfaces.AccountServiceInterface;
import org.bankingProject.jpt.bankingProject.services.users.interfaces.AdminServiceInterface;
import org.bankingProject.jpt.bankingProject.utils.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping ("/api")
public class AdminController {
    @Autowired
    private AdminServiceInterface adminServiceInterface;
    @Autowired
    private AccountServiceInterface accountServiceInterface;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/addAdminUser")
    @ResponseStatus(HttpStatus.CREATED)
    public Admin addNewAdminUser(@RequestBody @Valid Admin admin){
       //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String encodedPassword = passwordEncoder.encode(admin.getPassword());
        admin.setPassword(encodedPassword);
        return adminServiceInterface.addAdminUser(admin);
    }

    @GetMapping("/accountList")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> getAccountList() {
        return accountServiceInterface.getAccountList();
    }

    @GetMapping("/adminBalance/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Money getBalance(@PathVariable("id") long id) {
        return adminServiceInterface.viewBalance(id);
    }

    @PutMapping("/updateBalance")
    @ResponseStatus(HttpStatus.OK)
    public Money updateBalance(Long id, @RequestBody @Valid MoneyBalanceDTO amount) throws ResponseStatusException{
        return adminServiceInterface.updateBalance(id, amount);
    }

}
