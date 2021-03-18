package thesis.stocker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import thesis.stocker.DAO.IUserDAO;
import thesis.stocker.DTO.UserDTO;

import java.util.List;

@Component
public class UserService  implements IUserService{

    @Autowired
    IUserDAO userDAO;

    public void createUser(String name) throws Exception {
        UserDTO newUser = new UserDTO(name);
        this.save(newUser);
    }

    @Override
    public UserDTO fetchById(int id) {
        return null;
    }

    @Override
    public boolean save(UserDTO userDTO) throws Exception {
        userDAO.save(userDTO);
        return false;
    }

    @Override
    public List<UserDTO> fetchUsers() throws Exception {
        return null;
    }
}
