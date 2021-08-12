package cz.janicek.littlebanker.account.transaction;

import cz.janicek.littlebanker.account.AccountRepository;
import cz.janicek.littlebanker.exception.InsufficientFundsException;
import cz.janicek.littlebanker.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    // Create a new transaction between 2 accounts
    @PostMapping("/")
    public AccountTransaction create(@RequestBody AccountTransaction transaction) {
        return accountRepository.findByIban(transaction.getCreditorIBAN()).map(creditorAcc -> {
            double amount = transaction.getAmount();

            // Checking if the account has sufficient amount of funds.
            if (creditorAcc.getBalance() < amount) {
                throw new InsufficientFundsException("Account [accountId='" + creditorAcc.getAccountId() + "'] has insufficient balance.");
            }

            return accountRepository.findByIban(transaction.getDebtorIBAN()).map(debtorAcc -> {
                transaction.setDate(Calendar.getInstance().getTime());
                AccountTransaction savedTransaction = transactionRepository.save(transaction);

                creditorAcc.setBalance(creditorAcc.getBalance() - amount);
                creditorAcc.getTransactions().add(savedTransaction);
                debtorAcc.setBalance(debtorAcc.getBalance() + amount);
                debtorAcc.getTransactions().add(savedTransaction);
                accountRepository.save(creditorAcc);
                accountRepository.save(debtorAcc);
                return savedTransaction;
            }).orElseThrow(() -> new NotFoundException("Account with IBAN '" + transaction.getDebtorIBAN() + "' not found."));
        }).orElseThrow(() -> new NotFoundException("Account with IBAN '" + transaction.getCreditorIBAN() + "' not found."));
    }

    // List all transactions
    @GetMapping("/")
    public List<AccountTransaction> all() {
        return transactionRepository.findAll();
    }

    // Search transactions
    @GetMapping("/search")
    public List<AccountTransaction> search(
            @RequestParam(required = false) Double amount,
            @RequestParam(required = false) String iban,
            @RequestParam(required = false) String message) {
        List<AccountTransaction> amountFound = new ArrayList<>();
        List<AccountTransaction> ibanFound = new ArrayList<>();
        List<AccountTransaction> messageFound = new ArrayList<>();
        if (amount != null) {
            amountFound = transactionRepository.findByAmount(amount);
        }
        if (iban != null) {
            List<AccountTransaction> debtorList = transactionRepository.findByDebtorIBAN(iban);
            List<AccountTransaction> creditorList = transactionRepository.findByCreditorIBAN(iban);
            ibanFound = Stream.concat(debtorList.stream(), creditorList.stream()).collect(Collectors.toList());
        }
        if (message != null) {
            messageFound = transactionRepository.findByMessageContains(message);
        }
        return Stream.of(amountFound, ibanFound, messageFound).flatMap(Collection::stream).collect(Collectors.toList());
    }
}
