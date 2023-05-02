package org.bankingProject.jpt.bankingProject.services.accounts.interfaces;

import org.bankingProject.jpt.bankingProject.models.TransferMoney;
import org.bankingProject.jpt.bankingProject.models.accounts.Account;
import org.bankingProject.jpt.bankingProject.utils.Money;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;
import java.util.List;

public interface AccountServiceInterface {
    List<Account> getAccountList();
    Account deleteAccount(Long id, Account account);
    TransferMoney transferMoney(TransferMoney transferMoney) throws AccountNotFoundException;
    Money deposit(Long id, BigDecimal amount) throws AccountNotFoundException;
    Money withdraw(Long id, BigDecimal amount) throws AccountNotFoundException;
    TransferMoney thirdPartyDeposit(TransferMoney transferMoney, String hashKey) throws AccountNotFoundException;

    TransferMoney thirdPartyWithdraw(TransferMoney transferMoney, String hashKey) throws AccountNotFoundException;
}