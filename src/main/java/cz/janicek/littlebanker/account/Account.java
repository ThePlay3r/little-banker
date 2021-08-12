package cz.janicek.littlebanker.account;

import cz.janicek.littlebanker.account.transaction.AccountTransaction;
import cz.janicek.littlebanker.customer.Customer;
import cz.janicek.littlebanker.enums.Currency;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Account {

    @Id
    @GeneratedValue
    private int accountId;

    private String iban;
    private Currency currency = Currency.CZK;
    private Double balance = 1500.00;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToMany
    private List<AccountTransaction> transactions;
}
