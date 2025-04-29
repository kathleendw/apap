package apapedia.user.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apapedia.user.model.Customer;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface CustomerDb extends JpaRepository<Customer,UUID>{
    Customer findCustomerByIdUser(UUID id);
    Customer findByUsername(String username);
}
