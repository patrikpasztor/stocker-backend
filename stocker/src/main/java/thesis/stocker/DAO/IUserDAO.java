package thesis.stocker.DAO;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import thesis.stocker.model.User;

import java.util.Map;

public interface IUserDAO {
    User save(User user) throws Exception;

    User findByName(String name);

    Double getStockAmount(String name, String stock);

    User findByEmail(String email) throws UsernameNotFoundException;
}
