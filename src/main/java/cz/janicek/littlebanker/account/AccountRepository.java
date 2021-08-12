package cz.janicek.littlebanker.account;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    List<Account> findByCustomerCustomerId(int customerId);

    Optional<Account> findByIban(String iban);
}
