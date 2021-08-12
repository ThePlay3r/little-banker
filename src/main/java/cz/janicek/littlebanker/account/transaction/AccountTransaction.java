package cz.janicek.littlebanker.account.transaction;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Setter
@Getter
@Entity
public class AccountTransaction {

    @Id
    @GeneratedValue
    private long transactionId;

    @CreatedDate
    private Date date;

    private Double amount;
    private String creditorIBAN;
    private String debtorIBAN;
    private String message;
}
