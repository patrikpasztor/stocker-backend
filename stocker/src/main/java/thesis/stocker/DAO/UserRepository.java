package thesis.stocker.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import thesis.stocker.model.User;

import java.util.Map;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByName(String name);
}
