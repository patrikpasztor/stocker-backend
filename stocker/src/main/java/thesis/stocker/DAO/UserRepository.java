package thesis.stocker.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import thesis.stocker.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByName(String name);
}
