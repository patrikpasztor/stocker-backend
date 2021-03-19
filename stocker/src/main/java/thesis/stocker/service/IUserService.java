package thesis.stocker.service;

import thesis.stocker.DTO.UserDTO;
import thesis.stocker.model.User;

import java.util.List;

public interface IUserService {

    UserDTO findById(int id);

    User findByName(String name);

    boolean save(UserDTO userDTO) throws Exception;

    List<UserDTO> findAll() throws Exception;

}
