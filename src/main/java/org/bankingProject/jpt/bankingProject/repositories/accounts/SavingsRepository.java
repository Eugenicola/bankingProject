package org.bankingProject.jpt.bankingProject.repositories.accounts;

import org.bankingProject.jpt.bankingProject.models.accounts.Savings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavingsRepository extends JpaRepository<Savings, Long> {
}
