package org.bankingProject.jpt.bankingProject.repositories.accounts;

import org.bankingProject.jpt.bankingProject.models.accounts.Account;
import org.bankingProject.jpt.bankingProject.securityConfig.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account,Long> {

    @Query(value = "SELECT account_balance_currency, account_balance_amount FROM Account JOIN  user on account.user_account = user.id AND account.user_account_secondary = user.id", nativeQuery = true )
    List<Account> findAllByPrimaryOrSecondaryOwner(User primaryOwnerId, User secondaryOwnerId);
}
