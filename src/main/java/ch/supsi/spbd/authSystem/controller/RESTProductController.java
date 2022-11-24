package ch.supsi.spbd.authSystem.controller;

import ch.supsi.spbd.authSystem.model.Customer;
import ch.supsi.spbd.authSystem.model.Product;
import ch.supsi.spbd.authSystem.service.CustomerService;
import ch.supsi.spbd.authSystem.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products/*")
public class RESTProductController {
    @Autowired
    private ProductService service;
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable long id){
        return new ResponseEntity<>(service.getProduct(id), HttpStatus.OK);
    }
    @GetMapping("/")

    public ResponseEntity<List<Product>> getAll(){
        return new ResponseEntity<>(service.getProducts(), HttpStatus.OK);
    }
    @PostMapping("/add")
    @PreAuthorize("hasRole('admin_app')")
    public ResponseEntity<Product> setCustomer(Product p){
        return new ResponseEntity<>(service.addProduct(p), HttpStatus.OK);
    }
    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('admin_app')")
    public  ResponseEntity<List<Map.Entry<String,Boolean>>> setCustomer(@PathVariable long id){
        HashMap<String,Boolean> status=new HashMap<>();
        status.put("status",service.deleteProduct(id));
        return new ResponseEntity<>(status.entrySet().stream().limit(1).collect(Collectors.toList()),HttpStatus.OK);
    }
}
