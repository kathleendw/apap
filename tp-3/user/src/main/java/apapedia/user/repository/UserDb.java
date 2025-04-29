package apapedia.user.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apapedia.user.model.UserApapedia;
import jakarta.transaction.Transactional;

@Repository
public interface UserDb extends JpaRepository<UserApapedia,UUID>{
    // Optional<User> findByUsername(String username);
    UserApapedia findByUsername(String username);
    UserApapedia findByIdUser(UUID uuid);
}
