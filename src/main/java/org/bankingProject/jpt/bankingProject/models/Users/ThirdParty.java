package org.bankingProject.jpt.bankingProject.models.Users;

import jakarta.persistence.Entity;
import lombok.Data;
import org.bankingProject.jpt.bankingProject.securityConfig.models.User;

import java.util.UUID;

@Data
@Entity
public class ThirdParty extends User {
    private String hashedKey = UUID.randomUUID().toString();
}
