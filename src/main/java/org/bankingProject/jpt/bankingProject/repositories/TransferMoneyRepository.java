package org.bankingProject.jpt.bankingProject.repositories;

import org.bankingProject.jpt.bankingProject.models.TransferMoney;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferMoneyRepository extends JpaRepository<TransferMoney, Long> {
}
