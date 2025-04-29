package apapedia.order.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apapedia.order.model.Cart;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface CartDb extends JpaRepository<Cart, UUID> {

    List<Cart> findAll();
    
    Cart findCartByCartId(UUID cartId);
    
    Cart findCartByUserId(UUID userId);
}
