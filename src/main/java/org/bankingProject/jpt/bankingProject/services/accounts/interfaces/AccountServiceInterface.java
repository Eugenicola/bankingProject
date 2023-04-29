package org.bankingProject.jpt.bankingProject.services.accounts.interfaces;

import org.bankingProject.jpt.bankingProject.models.TransferMoney;
import org.bankingProject.jpt.bankingProject.models.accounts.Account;
import org.bankingProject.jpt.bankingProject.utils.Money;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;

public interface AccountServiceInterface {

    Account deleteAccount(Long id, Account account);
    TransferMoney transferMoney(TransferMoney transferMoney) throws AccountNotFoundException;
    Money deposit(Long id, BigDecimal amount) throws AccountNotFoundException;
    Money withdraw(Long id, BigDecimal amount) throws AccountNotFoundException;
}