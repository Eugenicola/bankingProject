package org.bankingProject.jpt.bankingProject.services.users.interfaces;

import org.bankingProject.jpt.bankingProject.models.TransferMoney;
import org.bankingProject.jpt.bankingProject.models.Users.ThirdParty;

import javax.security.auth.login.AccountNotFoundException;

public interface ThirdPartyServiceInterface {

   ThirdParty addThirdParty (ThirdParty thirdParty);

}
