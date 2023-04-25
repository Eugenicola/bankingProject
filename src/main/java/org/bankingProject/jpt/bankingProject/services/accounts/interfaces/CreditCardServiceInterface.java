package org.bankingProject.jpt.bankingProject.services.accounts.interfaces;

import org.bankingProject.jpt.bankingProject.models.accounts.Account;
import org.bankingProject.jpt.bankingProject.models.accounts.CreditCard;

public interface CreditCardServiceInterface {

    Account createCreditCardAccount(CreditCard account);
}
