package thesis.stocker.service;

import thesis.stocker.DTO.StockDTO;
import thesis.stocker.DTO.TransactionDTO;
import thesis.stocker.model.ConfirmationToken;
import thesis.stocker.model.User;

import java.util.List;

public interface IUserService{

    User findById(int id);

    User findByName(String name);

    boolean save(User user) throws Exception;

    boolean updateBuy(TransactionDTO transactionDTO);

    boolean updateSell(TransactionDTO transactionDTO);

    Double getStockAmount(String name, String stock);

    List<StockDTO> listOwnedStocks(String user);

    Double getAverageBuyPrice(String email, String stock);

    void signUpUser(User user);

    void confirmUser(ConfirmationToken confirmationToken);

    void sendConfirmationMail(String userMail, String token);

    boolean authenticate(String email, String password);

    Double chargeBalance(String email, Double amount);

    Double getBalance(String email);

    Boolean watchStock(String email, String stock);

    Boolean stopWatchStock(String email, String stock);

    List<String> getWatchlistStocks(String email);
}
