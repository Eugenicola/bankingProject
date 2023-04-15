package org.bankingProject.jpt.bankingProject.securityConfig.models;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Admin {
    private String name;
}

/*
AccountHolders
The AccountHolders should be able to access their own accounts and only their accounts when passing the correct credentials using Basic Auth. AccountHolders have:
A name
Date of birth
A primaryAddress (which should be a separate address class)
An optional mailingAddress
Admins
Admins only have a name.
ThirdParty
The ThirdParty Accounts have a hashed key and a name. */