package thesis.stocker.DAO;

import thesis.stocker.model.User;

import java.util.Map;

public interface IUserDAO {
    boolean save(User user) throws Exception;

    User findByName(String name);

    Double getStockAmount(String name, String stock);
}
