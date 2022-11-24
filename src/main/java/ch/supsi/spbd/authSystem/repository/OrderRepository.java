package ch.supsi.spbd.authSystem.repository;

import ch.supsi.spbd.authSystem.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface OrderRepository extends JpaRepository<Order, Long> {
}
