package org.bankingProject.jpt.bankingProject.repositories.accounts;

import org.bankingProject.jpt.bankingProject.models.accounts.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
}
