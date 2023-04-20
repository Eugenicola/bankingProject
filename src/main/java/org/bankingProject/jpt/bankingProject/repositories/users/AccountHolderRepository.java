package org.bankingProject.jpt.bankingProject.repositories.users;

import org.bankingProject.jpt.bankingProject.models.Users.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountHolderRepository extends JpaRepository<AccountHolder, Long> {
}
