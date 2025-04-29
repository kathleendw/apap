package apapedia.order.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import apapedia.order.model.CartItem;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemDb extends JpaRepository<CartItem, UUID> {
    List<CartItem> findAll();

    List<CartItem> findByProductId(UUID id);

    CartItem findCartItemByItemId(UUID itemId);
}
