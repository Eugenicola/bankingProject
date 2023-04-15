package org.bankingProject.jpt.bankingProject.securityConfig.models;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Data;
import org.bankingProject.jpt.bankingProject.utils.Address;

import java.util.Date;
import java.util.Optional;

@Data
@Entity
public class AccountHolders {
    private String name;
    private Date birthDate;
    @Embedded
    private Address primaryAddress;
    private Optional<String> mailingAddress;
}
//A name
//Date of birth
//A primaryAddress (which should be a separate address class)
//An optional mailingAddress