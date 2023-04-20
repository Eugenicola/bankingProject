package org.bankingProject.jpt.bankingProject.repositories.users;

import org.bankingProject.jpt.bankingProject.models.Users.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
