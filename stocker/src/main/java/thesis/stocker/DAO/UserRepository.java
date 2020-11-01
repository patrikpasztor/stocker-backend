package thesis.stocker.DAO;

import org.springframework.data.repository.CrudRepository;
import thesis.stocker.DTO.UserDTO;

public interface UserRepository extends CrudRepository<UserDTO, Integer> {
}
