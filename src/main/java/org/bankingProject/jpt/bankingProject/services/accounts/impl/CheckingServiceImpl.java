package org.bankingProject.jpt.bankingProject.services.accounts.impl;

import org.bankingProject.jpt.bankingProject.models.Users.AccountHolder;
import org.bankingProject.jpt.bankingProject.models.accounts.Account;
import org.bankingProject.jpt.bankingProject.models.accounts.Checking;
import org.bankingProject.jpt.bankingProject.models.accounts.StudentChecking;
import org.bankingProject.jpt.bankingProject.repositories.accounts.CheckingRepository;
import org.bankingProject.jpt.bankingProject.repositories.accounts.StudentCheckingRepository;
import org.bankingProject.jpt.bankingProject.repositories.users.AccountHolderRepository;
import org.bankingProject.jpt.bankingProject.services.accounts.interfaces.CheckingServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class CheckingServiceImpl implements CheckingServiceInterface {

    @Autowired
    private CheckingRepository checkingRepository;
    @Autowired
    private StudentCheckingRepository studentCheckingRepository;
    @Autowired
    private AccountHolderRepository accountHolderRepository;

    public Account createCheckingAccount(Checking account)  {
        Instant inst = Instant.now();
        Date currentDate = Date.from(inst);
        AccountHolder primaryOwner = accountHolderRepository.findById(account.getPrimaryOwner().getId()).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found, please check the ID"));
        long age = currentDate.getTime() - primaryOwner.getBirthDate().getTime();
        long ageInYears = TimeUnit.MILLISECONDS.toDays(age) / 365;
            if (ageInYears >= 24) {
                return checkingRepository.save(account);
            }else {
                checkingToStudentChecking(account);
                return account;
            }
    }

    public void checkingToStudentChecking(Checking account){
        StudentChecking studentChecking = new StudentChecking();
        studentChecking.setId(account.getId());
        studentChecking.setPrimaryOwner(account.getPrimaryOwner());
        studentChecking.setSecondaryOwner(account.getSecondaryOwner());
        studentChecking.setPenaltyFee(account.getPenaltyFee());
        studentChecking.setCreationDate(account.getCreationDate());
        studentChecking.setBalance(account.getBalance());
        studentChecking.setSecretKey(account.getSecretKey());
        studentChecking.setStatus(account.getStatus());
        studentCheckingRepository.save(studentChecking);
    }

}
