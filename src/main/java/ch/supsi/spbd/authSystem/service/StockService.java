package ch.supsi.spbd.authSystem.service;

import ch.supsi.spbd.authSystem.model.Product;
import ch.supsi.spbd.authSystem.model.Stock;
import ch.supsi.spbd.authSystem.repository.ProductRepository;
import ch.supsi.spbd.authSystem.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Component
public class StockService {
    @Autowired
    StockRepository repository;

    public List<Stock> getStocks() {
        return repository.findAll();
    }

    public Stock getStock(long id) {
        Optional<Stock> c = repository.findById(id);
        if (c.isPresent())
            return c.get();
        else
            return null;
    }
    public Stock addStock(Stock product) {

        return (Stock) repository.save(product);
    }
    public boolean deleteStock(long stock) {
        try{
            repository.deleteById(stock);
            return true;
        }catch (Exception e)
        {
            return  false;
        }

    }
}
