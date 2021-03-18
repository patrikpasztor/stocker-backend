package thesis.stocker.service;

import org.springframework.stereotype.Service;
import thesis.stocker.DTO.TransactionDTO;
import thesis.stocker.DTO.UserDTO;
import thesis.stocker.model.Transaction;
import thesis.stocker.model.User;

@Service
public class MapperService {

    private UserDTO userToDto(User user) {
        UserDTO userDTO = new UserDTO();

        return userDTO;
    }

    private User dtoToUser(UserDTO userDTO) {
        User user = new User();

        return user;
    }

    private TransactionDTO transactionToDto(Transaction transaction) {
        TransactionDTO transactionDTO = new TransactionDTO();

        return transactionDTO;
    }

    private Transaction dtoToTransaction(TransactionDTO transactionDTO) {
        Transaction transaction = new Transaction();

        return transaction;
    }
}
