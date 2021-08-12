package cz.janicek.littlebanker.account.transaction;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<AccountTransaction, Integer> {
    List<AccountTransaction> findByAmount(double amount);
    List<AccountTransaction> findByCreditorIBAN(String iban);
    List<AccountTransaction> findByDebtorIBAN(String iban);
    List<AccountTransaction> findByMessageContains(String message);
}
