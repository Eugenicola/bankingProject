package org.bankingProject.jpt.bankingProject.repositories.accounts;

import org.bankingProject.jpt.bankingProject.models.accounts.Checking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckingRepository extends JpaRepository<Checking, Long> {
}
