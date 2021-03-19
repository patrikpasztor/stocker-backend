package thesis.stocker.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import thesis.stocker.DTO.UserDTO;
import thesis.stocker.model.User;

@Component
public class UserDAO implements IUserDAO{

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean save(User user) throws Exception {
        userRepository.save(user);
        return false;
    }

    @Override
    public User findByName(String name) {
        return userRepository.findByName(name);
    }
}
