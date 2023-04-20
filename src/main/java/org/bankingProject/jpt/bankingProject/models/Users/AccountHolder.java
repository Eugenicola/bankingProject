package org.bankingProject.jpt.bankingProject.models.Users;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Data;
import org.bankingProject.jpt.bankingProject.securityConfig.models.User;
import org.bankingProject.jpt.bankingProject.utils.Address;

import java.util.Date;
import java.util.Optional;

@Data
@Entity
public class AccountHolder extends User {

    private Date birthDate;
    @Embedded
    private Address primaryAddress;
    private String mailingAddress;
}
//A name
//Date of birth
//A primaryAddress (which should be a separate address class)
//An optional mailingAddress