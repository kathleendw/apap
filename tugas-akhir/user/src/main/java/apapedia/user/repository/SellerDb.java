package apapedia.user.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apapedia.user.model.Seller;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface SellerDb extends JpaRepository<Seller,UUID>{
    Seller findSellerByIdUser(UUID id);
}