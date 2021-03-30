package thesis.stocker.service;

import thesis.stocker.DTO.StockDTO;
import thesis.stocker.DTO.TransactionDTO;
import thesis.stocker.DTO.UserDTO;
import thesis.stocker.model.User;

import java.util.List;
import java.util.Map;

public interface IUserService {

    User findById(int id);

    User findByName(String name);

    boolean save(User user) throws Exception;

    List<UserDTO> findAll() throws Exception;

    boolean updateBuy(TransactionDTO transactionDTO);

    boolean updateSell(TransactionDTO transactionDTO);

    Double getStockAmount(String name, String stock);

    List<StockDTO> listOwnedStocks(String user);

    Double getAverageBuyPrice(String user, String stock);

}
