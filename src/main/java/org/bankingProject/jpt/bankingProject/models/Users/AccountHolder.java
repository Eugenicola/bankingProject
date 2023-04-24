package org.bankingProject.jpt.bankingProject.models.Users;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bankingProject.jpt.bankingProject.securityConfig.models.User;
import org.bankingProject.jpt.bankingProject.utils.Address;


import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AccountHolder extends User {
    private Date birthDate;
    @Embedded
    private Address primaryAddress;

    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column (name = "billing_street")),
            @AttributeOverride(name = "city", column = @Column(name = "billing_city")),
            @AttributeOverride(name = "state", column = @Column (name = "billing_state")),
            @AttributeOverride(name = "zipCode", column = @Column (name = "billing_zip_code"))
    })
    @Embedded
    private Address mailingAddress;


}
//A name
//Date of birth
//A primaryAddress (which should be a separate address class)
//An optional mailingAddress