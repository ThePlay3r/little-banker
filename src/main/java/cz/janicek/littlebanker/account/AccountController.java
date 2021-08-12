package cz.janicek.littlebanker.account;

import cz.janicek.littlebanker.customer.CustomerRepository;
import cz.janicek.littlebanker.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer/{customerId}/account")
public class AccountController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountRepository accountRepository;

    // Creating a new Account
    @PostMapping("/")
    public Account create(@PathVariable int customerId, @RequestBody Account account) {
        return customerRepository.findById(customerId).map(customer -> {
            account.setCustomer(customer);
            accountRepository.save(account);
            return account;
        }).orElseThrow(() -> new NotFoundException("Customer with ID '" + customerId + "' not found."));
    }

    // Deleting Account
    @DeleteMapping("/{accountId}")
    public ResponseEntity<?> delete(@PathVariable int customerId, @PathVariable int accountId) {

        if (customerRepository.existsById(customerId)) {
            return accountRepository.findById(accountId).map(account -> {
                accountRepository.delete(account);
                return ResponseEntity.ok().build();
            }).orElseThrow(() -> new NotFoundException("Account with ID '" + accountId + "' not found."));
        } else {
            throw new NotFoundException("Customer with ID '" + customerId + "' not found.");
        }
    }

    // Modifying Account
    @PutMapping("/{accountId}")
    public ResponseEntity<Account> update(@PathVariable int customerId, @PathVariable int accountId, @RequestBody Account newAccount) {
        return customerRepository.findById(customerId).map(customer -> {
            return accountRepository.findById(accountId).map(account -> {
                account.setIban(newAccount.getIban());
                account.setCurrency(newAccount.getCurrency());
                account.setBalance(newAccount.getBalance());
                account.setCustomer(customer);
                accountRepository.save(account);
                return ResponseEntity.ok(account);
            }).orElseThrow(() -> new NotFoundException("Account with ID '" + customerId + "' not found."));
        }).orElseThrow(() -> new NotFoundException("Customer with ID '" + customerId + "' not found."));
    }

    // Listing all Accounts
    @GetMapping("/")
    public List<Account> all(@PathVariable int customerId) {
        return accountRepository.findByCustomerCustomerId(customerId);
    }
}
