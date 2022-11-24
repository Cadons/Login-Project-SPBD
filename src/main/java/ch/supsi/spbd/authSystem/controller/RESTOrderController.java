package ch.supsi.spbd.authSystem.controller;

import ch.supsi.spbd.authSystem.model.Order;
import ch.supsi.spbd.authSystem.model.Product;
import ch.supsi.spbd.authSystem.service.OrderService;
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
@RequestMapping("/orders/*")
public class RESTOrderController {
    @Autowired
    private OrderService service;
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('user_app')")
    public ResponseEntity<Order> getProduct(@PathVariable long id){
        return new ResponseEntity<>(service.getOrder(id), HttpStatus.OK);
    }
    @GetMapping("/")
    @PreAuthorize("hasRole('user_app')")
    public ResponseEntity<List<Order>> getAll(){
        return new ResponseEntity<>(service.getOrders(), HttpStatus.OK);
    }
    @PostMapping("/add")
    @PreAuthorize("hasRole('user_app')")
    public ResponseEntity<Order> setCustomer(Order p){
        return new ResponseEntity<>(service.addOrder(p), HttpStatus.OK);
    }
    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('user_app')")
    public  ResponseEntity<List<Map.Entry<String,Boolean>>> setCustomer(@PathVariable long id){
        HashMap<String,Boolean> status=new HashMap<>();
        status.put("status",service.deleteOrder(id));
        return new ResponseEntity<>(status.entrySet().stream().limit(1).collect(Collectors.toList()),HttpStatus.OK);
    }
}
