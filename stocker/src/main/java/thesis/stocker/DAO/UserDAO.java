package thesis.stocker.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import thesis.stocker.DTO.UserDTO;

@Component
public class UserDAO implements IUserDAO{

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean save(UserDTO userDTO) throws Exception {
        userRepository.save(userDTO);
        return false;
    }
}
