package org.bankingProject.jpt.bankingProject.models.Users;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bankingProject.jpt.bankingProject.models.accounts.Account;
import org.bankingProject.jpt.bankingProject.securityConfig.models.Role;
import org.bankingProject.jpt.bankingProject.securityConfig.models.User;
import org.bankingProject.jpt.bankingProject.utils.Address;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AccountHolder extends User {
    private Date birthDate;
    @Embedded
    private Address primaryAddress;
    private String mailingAddress;

    public AccountHolder(Long id, String name,@NotNull String username, @NotNull String password, Set<Account> accounts, Set<Account> accountsSecondary, Collection<Role> roles, Date birthDate, Address primaryAddress, String mailingAddress) {
        super(id, name, username, password, accounts, accountsSecondary, roles);
        this.birthDate = birthDate;
        this.primaryAddress = primaryAddress;
        this.mailingAddress = mailingAddress;
    }
}
//A name
//Date of birth
//A primaryAddress (which should be a separate address class)
//An optional mailingAddress