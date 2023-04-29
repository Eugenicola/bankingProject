package org.bankingProject.jpt.bankingProject.models.accounts;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bankingProject.jpt.bankingProject.models.Users.AccountHolder;
import org.bankingProject.jpt.bankingProject.utils.Money;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column (name = "account_balance_amount")),
            @AttributeOverride(name = "currency", column = @Column (name = "account_balance_currency"))
    })
    Money balance;
    @ManyToOne
    @JoinColumn(name="user_account")
    private AccountHolder primaryOwner;
    @ManyToOne
    @JoinColumn(name="user_account_secondary")
    private AccountHolder secondaryOwner;
    private BigDecimal penaltyFee = new BigDecimal(40) ;
    private Date creationDate;

    public void applyPenalty(BigDecimal minimumBalance){
        if(balance.getAmount().compareTo(minimumBalance)<0){
            setBalance(new Money(balance.getAmount().subtract(penaltyFee)));
        }
    }


    public void withdraw(BigDecimal amount) {
        if (balance.getAmount().compareTo(amount)<0) {
            System.out.println("No tienes suficiente saldo.");
        } else {
            setBalance(new Money(balance.getAmount().subtract(amount)));
            System.out.println("Se retiró " + amount + " de tu cuenta de cheques.");
        }
    }
    public void deposit(BigDecimal amount) {
        setBalance(new Money(balance.getAmount().add(amount)));
        System.out.println("Se depositó " + amount + " en tu cuenta de cheques.");
    }
}
