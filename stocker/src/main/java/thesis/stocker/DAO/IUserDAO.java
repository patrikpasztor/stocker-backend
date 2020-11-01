package thesis.stocker.DAO;

import thesis.stocker.DTO.UserDTO;

public interface IUserDAO {
    public boolean save(UserDTO userDTO) throws Exception;
}
