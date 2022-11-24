package ch.supsi.spbd.authSystem.service;

import ch.supsi.spbd.authSystem.model.Customer;
import ch.supsi.spbd.authSystem.model.Product;
import ch.supsi.spbd.authSystem.repository.CustomerRepository;
import ch.supsi.spbd.authSystem.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Component
public class ProductService {
    @Autowired
    ProductRepository repository;

    public List<Product> getProducts() {
        return repository.findAll();
    }

    public Product getProduct(long id) {
        Optional<Product> c = repository.findById(id);
        if (c.isPresent())
            return c.get();
        else
            return null;
    }
    public Product addProduct(Product product) {

        return (Product) repository.save(product);
    }
    public boolean deleteProduct(long product) {
        try{
            repository.deleteById(product);
            return true;
        }catch (Exception e)
        {
            return  false;
        }

    }
}
