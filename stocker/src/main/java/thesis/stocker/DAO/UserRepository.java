package thesis.stocker.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import thesis.stocker.DTO.UserDTO;

public interface UserRepository extends JpaRepository<UserDTO, Integer> {
}
