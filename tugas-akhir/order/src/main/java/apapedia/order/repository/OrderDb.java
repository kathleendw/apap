package apapedia.order.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import apapedia.order.model.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDb extends JpaRepository<Order, UUID> {
    List<Order> findAllByCustomer(UUID userId);
    List<Order> findAllBySeller(UUID userId);
    // Order findById(UUID id);
}
