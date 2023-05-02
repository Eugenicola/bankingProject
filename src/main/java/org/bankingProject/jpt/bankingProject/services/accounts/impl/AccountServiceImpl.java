package org.bankingProject.jpt.bankingProject.services.accounts.impl;

import org.bankingProject.jpt.bankingProject.models.TransferMoney;
import org.bankingProject.jpt.bankingProject.models.accounts.Account;
import org.bankingProject.jpt.bankingProject.models.accounts.Checking;
import org.bankingProject.jpt.bankingProject.models.accounts.Savings;
import org.bankingProject.jpt.bankingProject.repositories.TransferMoneyRepository;
import org.bankingProject.jpt.bankingProject.repositories.accounts.AccountRepository;
import org.bankingProject.jpt.bankingProject.repositories.accounts.CheckingRepository;
import org.bankingProject.jpt.bankingProject.repositories.accounts.SavingsRepository;
import org.bankingProject.jpt.bankingProject.services.accounts.interfaces.AccountServiceInterface;
import org.bankingProject.jpt.bankingProject.services.users.impl.AccountHolderServiceImpl;
import org.bankingProject.jpt.bankingProject.utils.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountServiceInterface {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransferMoneyRepository transferMoneyRepository;
    @Autowired
    private SavingsRepository savingsRepository;
    @Autowired
    private CheckingRepository checkingRepository;
    @Autowired
    private AccountHolderServiceImpl accountHolderService;

    public Account deleteAccount (Long id, Account account){
        accountRepository.deleteById(id);
        return account;
    }
    public List<Account> getAccountList() {
        return accountRepository.findAll();
    }

    public void applyRules(Long id){
        Account account = accountRepository.findById(id).get();
        if(account instanceof Savings){
            Savings savings = (Savings) account;
            BigDecimal minimumBalance = ((Savings) account).getMinimumBalance();
            savings.applyPenalty(minimumBalance);
            savingsRepository.save(savings);
        } else if (account instanceof Checking) {
            Checking checking = (Checking) account;
            BigDecimal minimumBalance = ((Checking) account).getMinimumBalance();
            checking.applyPenalty(minimumBalance);
            checkingRepository.save(checking);
        }
    }

    public Money withdraw(Long id, BigDecimal amount) throws AccountNotFoundException {
        Account account = accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException("Account not found"));
        account.withdraw(amount);
        applyRules(id);
        accountRepository.save(account);
        return account.getBalance();
    }

    public Money deposit(Long id, BigDecimal amount) throws AccountNotFoundException {
        Account account = accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException("Account ID not found"));
        account.deposit(amount);
        applyRules(id);
        accountRepository.save(account);
        return account.getBalance();
    }

    public TransferMoney transferMoney(TransferMoney transferMoney) throws AccountNotFoundException{
        Account origin = accountRepository.findById(transferMoney.getOrigin()).orElseThrow(() -> new AccountNotFoundException("Account not found"));
        Account destiny = accountRepository.findById(transferMoney.getDestiny()).orElseThrow(() -> new AccountNotFoundException("Account not found"));
        if(!accountHolderService.accountOwner(origin.getId())){
          throw new ResponseStatusException(HttpStatus.FORBIDDEN ,"The action is not possible to complete");
        }
        origin.withdraw(transferMoney.getAmount());
        destiny.deposit(transferMoney.getAmount());
        accountRepository.save(origin);
        accountRepository.save(destiny);
        transferMoneyRepository.save(transferMoney);
        return transferMoney;
    }

    public TransferMoney thirdPartyDeposit(TransferMoney transferMoney, String hashKey) throws AccountNotFoundException {
        Account destiny = accountRepository.findById(transferMoney.getDestiny()).orElseThrow(() -> new AccountNotFoundException("Account not found"));
        if(hashKey == null || hashKey.isEmpty()){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN ,"HashKey is necessary to complete the transaction");
        }
        deposit(destiny.getId(), transferMoney.getAmount());
        accountRepository.save(destiny);
        transferMoneyRepository.save(transferMoney);
        return transferMoney;
    }

    public TransferMoney thirdPartyWithdraw(TransferMoney transferMoney, String hashKey) throws AccountNotFoundException {
        Account origin = accountRepository.findById(transferMoney.getDestiny()).orElseThrow(() -> new AccountNotFoundException("Account not found"));
        if(hashKey == null || hashKey.isEmpty()){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN ,"HashKey is necessary to complete the transaction");
        }
        withdraw(origin.getId(), transferMoney.getAmount());
        accountRepository.save(origin);
        transferMoneyRepository.save(transferMoney);
        return transferMoney;
    }

}
