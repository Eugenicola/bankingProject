package org.bankingProject.jpt.bankingProject.securityConfig.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bankingProject.jpt.bankingProject.models.accounts.Account;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import static jakarta.persistence.FetchType.EAGER;

/**
 * Entity class for representing a User in the database
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    /**
     * The unique identifier for the user
     */
    @Id
    /**
     * The id field is generated automatically by the database
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * The name of the user
     */
    private String name;

    /**
     * The username used to log in
     */
    @NotNull
    private String username;

    /**
     * The password used to log in
     */
    @NotNull
    private String password;

    /**
     * The roles that the user has
     */
    @OneToMany(mappedBy = "primaryOwner")
    private Set<Account> accounts;// do we need to add another mappedBy to link to SecondaryOwner?

    @OneToMany(mappedBy = "secondaryOwner")
    private Set<Account> accountsSecondary;

    @ManyToMany(fetch = EAGER)
    private Collection<Role> roles = new ArrayList<>();


}

