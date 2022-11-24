package ch.supsi.spbd.authSystem.service;

import ch.supsi.spbd.authSystem.model.Order;
import ch.supsi.spbd.authSystem.model.Product;
import ch.supsi.spbd.authSystem.repository.OrderRepository;
import ch.supsi.spbd.authSystem.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Component
public class OrderService {
    @Autowired
    OrderRepository repository;

    public List<Order> getOrders() {
        return (List<Order>) repository.findAll();
    }

    public Order getOrder(long id) {
        Optional<Order> c = repository.findById(id);
        if (c.isPresent())
            return c.get();
        else
            return null;
    }
    public Order addOrder(Order order) {

        return (Order) repository.save(order);
    }
    public boolean deleteOrder(long product) {
        try{
            repository.deleteById(product);
            return true;
        }catch (Exception e)
        {
            return  false;
        }

    }
}
