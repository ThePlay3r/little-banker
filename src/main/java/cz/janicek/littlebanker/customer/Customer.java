package cz.janicek.littlebanker.customer;

import cz.janicek.littlebanker.enums.Sex;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
@Entity
public class Customer {

    @Id
    @GeneratedValue
    private int customerId;

    private String name;
    private String surname;
    private Sex sex;
    private String nationality;
    private Date birthDate;

    private String cardNumber;
    private Date cardIssueDate;
    private Date cardExpiryDate;
}
