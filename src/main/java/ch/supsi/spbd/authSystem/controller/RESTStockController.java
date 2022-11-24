package ch.supsi.spbd.authSystem.controller;

import ch.supsi.spbd.authSystem.model.Product;
import ch.supsi.spbd.authSystem.model.Stock;
import ch.supsi.spbd.authSystem.service.ProductService;
import ch.supsi.spbd.authSystem.service.StockService;
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
@RequestMapping("/stock/*")
public class RESTStockController {
    @Autowired
    private StockService service;
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('admin_app')")
    public ResponseEntity<Stock> getProduct(@PathVariable long id){
        return new ResponseEntity<>(service.getStock(id), HttpStatus.OK);
    }
    @GetMapping("/")
    @PreAuthorize("hasRole('admin_app')")
    public ResponseEntity<List<Stock>> getAll(){
        return new ResponseEntity<>(service.getStocks(), HttpStatus.OK);
    }
    @PostMapping("/add")
    @PreAuthorize("hasRole('admin_app')")
    public ResponseEntity<Stock> setCustomer(Stock p){
        return new ResponseEntity<>(service.addStock(p), HttpStatus.OK);
    }
    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('admin_app')")
    public  ResponseEntity<List<Map.Entry<String,Boolean>>> setCustomer(@PathVariable long id){
        HashMap<String,Boolean> status=new HashMap<>();
        status.put("status",service.deleteStock(id));
        return new ResponseEntity<>(status.entrySet().stream().limit(1).collect(Collectors.toList()),HttpStatus.OK);
    }
}
