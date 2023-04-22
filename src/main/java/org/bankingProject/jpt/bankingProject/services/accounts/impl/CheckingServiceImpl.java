package org.bankingProject.jpt.bankingProject.services.accounts.impl;

import org.bankingProject.jpt.bankingProject.models.Users.AccountHolder;
import org.bankingProject.jpt.bankingProject.models.accounts.Account;
import org.bankingProject.jpt.bankingProject.models.accounts.Checking;
import org.bankingProject.jpt.bankingProject.models.accounts.StudentChecking;
import org.bankingProject.jpt.bankingProject.repositories.accounts.CheckingRepository;
import org.bankingProject.jpt.bankingProject.repositories.accounts.StudentCheckingRepository;
import org.bankingProject.jpt.bankingProject.repositories.users.AccountHolderRepository;
import org.bankingProject.jpt.bankingProject.securityConfig.models.User;
import org.bankingProject.jpt.bankingProject.services.accounts.interfaces.CheckingServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.time.Period;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class CheckingServiceImpl implements CheckingServiceInterface {

    @Autowired
    private CheckingRepository checkingRepository;

    @Autowired
    private StudentCheckingRepository studentCheckingRepository;

    @Autowired
    private AccountHolderRepository accountHolderRepository;

    public Account createAccount(Account account)  {
        Instant inst = Instant.now();
        Date currentDate = Date.from(inst);
        //if the primaryOwner is less than 24,
        AccountHolder primaryOwner = accountHolderRepository.findById(account.getPrimaryOwner().getId()).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found, please check the ID"));
        long age = currentDate.getTime() - primaryOwner.getBirthDate().getTime();
        long ageInYears = TimeUnit.MILLISECONDS.toDays(age) / 365;
            if (ageInYears >= 24) {
                Checking checkingAcc = (Checking) account;
                return checkingRepository.save(checkingAcc);
            }else {
                StudentChecking studentCheckingAcc = (StudentChecking) account;
                return studentCheckingRepository.save(studentCheckingAcc);
            }
    }
    //When creating a new Checking account,
    // if the primaryOwner is less than 24, a StudentChecking account should be created
    // otherwise a regular Checking Account should be created.

}
