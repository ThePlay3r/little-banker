package cz.janicek.littlebanker.customer;

import cz.janicek.littlebanker.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    // Creating new Customer
    @PostMapping("/")
    public Customer create(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    // Deleting Customer
    @DeleteMapping("/{customerId}")
    public ResponseEntity<?> delete(@PathVariable int customerId) throws Exception {
        return customerRepository.findById(customerId).map(customer -> {
            customerRepository.delete(customer);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new NotFoundException("Customer with ID '" + customerId + "' not found."));
    }

    // Modifying Customer
    @PutMapping("/{customerId}")
    public ResponseEntity<Customer> update(@PathVariable int customerId, @RequestBody Customer newCustomer) {
        return customerRepository.findById(customerId).map(customer -> {
            customer.setName(newCustomer.getName());
            customer.setSurname(newCustomer.getSurname());
            customer.setSex(newCustomer.getSex());
            customer.setNationality(newCustomer.getNationality());
            customer.setBirthDate(newCustomer.getBirthDate());
            customer.setCardNumber(newCustomer.getCardNumber());
            customer.setCardIssueDate(newCustomer.getCardIssueDate());
            customer.setCardExpiryDate(newCustomer.getCardExpiryDate());
            customerRepository.save(customer);
            return ResponseEntity.ok(customer);
        }).orElseThrow(() -> new NotFoundException("Customer with ID '" + customerId + "' not found."));
    }

    // Listing all Customers
    @GetMapping("/")
    public List<Customer> all() {
        return customerRepository.findAll();
    }

    // Finding a specific Customer by ID
    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> findById(@PathVariable int customerId) {
        return customerRepository.findById(customerId)
                .map(ResponseEntity::ok).orElseThrow(() -> new NotFoundException("Customer with ID '" + customerId + "' not found."));
    }
}
