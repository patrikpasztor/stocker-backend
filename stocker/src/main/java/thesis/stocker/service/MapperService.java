package thesis.stocker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thesis.stocker.DTO.TransactionDTO;
import thesis.stocker.DTO.UserDTO;
import thesis.stocker.model.Transaction;
import thesis.stocker.model.User;

@Service
public class MapperService {

    @Autowired
    IUserService userService;

    public User dtoToUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setBalance(userDTO.getBalance());

        return user;
    }

    public Transaction dtoToTransaction(TransactionDTO transactionDTO) {
        Transaction transaction = new Transaction();
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setStock(transactionDTO.getStock());
        transaction.setUser(userService.findByName(transactionDTO.getUser()));
        transaction.setStockPrice(transactionDTO.getStockPrice());
        return transaction;
    }
}
