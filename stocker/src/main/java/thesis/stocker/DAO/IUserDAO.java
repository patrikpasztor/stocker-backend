package thesis.stocker.DAO;

import thesis.stocker.DTO.UserDTO;
import thesis.stocker.model.User;

public interface IUserDAO {
    boolean save(User user) throws Exception;

    User findByName(String name);

    Double getStockAmount(String name, String stock);
}
