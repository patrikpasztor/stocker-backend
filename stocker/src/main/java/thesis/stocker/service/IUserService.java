package thesis.stocker.service;

import thesis.stocker.DTO.UserDTO;

import java.util.List;

public interface IUserService {

    UserDTO fetchById(int id);

    boolean save(UserDTO userDTO) throws Exception;

    List<UserDTO> fetchUsers() throws Exception;

}
