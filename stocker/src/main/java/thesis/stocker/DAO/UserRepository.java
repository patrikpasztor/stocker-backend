package thesis.stocker.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import thesis.stocker.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByName(String name);
    Optional<User> findByEmail(String email);
}
