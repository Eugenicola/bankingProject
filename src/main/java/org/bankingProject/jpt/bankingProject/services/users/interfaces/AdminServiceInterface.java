package org.bankingProject.jpt.bankingProject.services.users.interfaces;

import org.bankingProject.jpt.bankingProject.dtos.MoneyBalanceDTO;
import org.bankingProject.jpt.bankingProject.models.Users.Admin;
import org.bankingProject.jpt.bankingProject.utils.Money;
import org.springframework.web.server.ResponseStatusException;

public interface AdminServiceInterface {
    Admin addAdminUser (Admin admin);
    Money viewBalance(long id) ;
    Money updateBalance(Long id, MoneyBalanceDTO amount) throws ResponseStatusException;

}
