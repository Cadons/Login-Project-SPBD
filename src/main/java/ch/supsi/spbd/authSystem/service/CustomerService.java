package ch.supsi.spbd.authSystem.service;

import ch.supsi.spbd.authSystem.model.Customer;
import ch.supsi.spbd.authSystem.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Component
public class CustomerService {
    @Autowired
    CustomerRepository repository;

    public List<Customer> getCustomers() {
        return (List<Customer>) repository.findAll();
    }

    public Customer getCustomer(int id) {
        Optional<Customer> c = repository.findById(id);
        if (c.isPresent())
            return c.get();
        else
            return null;
    }
    public Customer addCustomer(Customer customer) {

        return (Customer) repository.save(customer);
    }
}
