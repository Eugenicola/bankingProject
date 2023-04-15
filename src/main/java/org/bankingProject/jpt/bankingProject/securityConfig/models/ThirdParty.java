package org.bankingProject.jpt.bankingProject.securityConfig.models;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class ThirdParty {
    private String name;
    private long hashedKey;
}
