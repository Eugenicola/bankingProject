package org.bankingProject.jpt.bankingProject.repositories.users;

import org.bankingProject.jpt.bankingProject.models.Users.ThirdParty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThirdPartyRepository extends JpaRepository<ThirdParty, Long> {
}
