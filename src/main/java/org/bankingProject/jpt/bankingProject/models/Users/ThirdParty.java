package org.bankingProject.jpt.bankingProject.models.Users;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.bankingProject.jpt.bankingProject.securityConfig.models.User;

@Data
@Entity
public class ThirdParty extends User {
    private String name;
    private long hashedKey;
}
