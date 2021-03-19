package thesis.stocker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import thesis.stocker.DAO.IUserDAO;
import thesis.stocker.DTO.UserDTO;
import thesis.stocker.model.User;

import java.util.List;

@Component
public class UserService  implements IUserService{

    @Autowired
    IUserDAO userDAO;

    @Autowired
    MapperService mapperService;

    @Override
    public UserDTO findById(int id) {
        return null;
    }

    @Override
    public User findByName(String name) {
        return userDAO.findByName(name);
    }

    @Override
    public boolean save(UserDTO userDTO) throws Exception {
        User user = mapperService.dtoToUser(userDTO);
        userDAO.save(user);
        return false;
    }

    @Override
    public List<UserDTO> findAll() throws Exception {
        return null;
    }
}
