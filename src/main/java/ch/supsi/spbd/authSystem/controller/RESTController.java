package ch.supsi.spbd.authSystem.controller;

import ch.supsi.spbd.authSystem.model.Customer;
import ch.supsi.spbd.authSystem.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer/*")
public class RESTController {
    @Autowired
    private CustomerService service;
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('user_app')")
    public ResponseEntity<Customer> getCustomer(@PathVariable int id){
        return new ResponseEntity<>(service.getCustomer(id), HttpStatus.OK);
    }
    @GetMapping("/")
    @PreAuthorize("hasRole('admin_app')")
    public ResponseEntity<List<Customer>> getAllCustomers(){
        return new ResponseEntity<>(service.getCustomers(), HttpStatus.OK);
    }
    @PostMapping("/add")
    @PreAuthorize("hasRole('admin_app')")
    public ResponseEntity<Customer> setCustomer(Customer customer){
        return new ResponseEntity<>(service.addCustomer(customer), HttpStatus.OK);
    }
}
