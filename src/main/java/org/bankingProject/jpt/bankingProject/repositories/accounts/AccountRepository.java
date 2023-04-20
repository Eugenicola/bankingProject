package org.bankingProject.jpt.bankingProject.repositories.accounts;

import org.bankingProject.jpt.bankingProject.models.accounts.Account;
import org.bankingProject.jpt.bankingProject.securityConfig.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {

    public Account findByPrimaryOwnerOrSecondaryOwner(User primary, User secondary);
}
