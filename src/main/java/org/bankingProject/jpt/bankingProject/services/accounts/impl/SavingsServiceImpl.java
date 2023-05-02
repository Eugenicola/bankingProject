package org.bankingProject.jpt.bankingProject.services.accounts.impl;

import org.bankingProject.jpt.bankingProject.models.Users.AccountHolder;
import org.bankingProject.jpt.bankingProject.models.accounts.Account;
import org.bankingProject.jpt.bankingProject.models.accounts.Checking;
import org.bankingProject.jpt.bankingProject.models.accounts.Savings;
import org.bankingProject.jpt.bankingProject.repositories.accounts.SavingsRepository;
import org.bankingProject.jpt.bankingProject.repositories.users.AccountHolderRepository;
import org.bankingProject.jpt.bankingProject.services.accounts.interfaces.SavingsServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.Date;
import java.util.concurrent.TimeUnit;
@Service
public class SavingsServiceImpl implements SavingsServiceInterface {

    @Autowired
    private SavingsRepository savingsRepository;
    @Autowired
    private AccountHolderRepository accountHolderRepository;

    public Account createSavingsAccount(Savings account)  {
            return savingsRepository.save(account);

    }
}
