package thesis.stocker.service;

import thesis.stocker.DTO.TransactionDTO;
import thesis.stocker.DTO.UserDTO;
import thesis.stocker.model.User;

import java.util.List;

public interface IUserService {

    User findById(int id);

    User findByName(String name);

    boolean save(User user) throws Exception;

    List<UserDTO> findAll() throws Exception;

    boolean updateBuy(TransactionDTO transactionDTO);

    boolean updateSell(TransactionDTO transactionDTO);

    Double getStockAmount(String name, String stock);

}
