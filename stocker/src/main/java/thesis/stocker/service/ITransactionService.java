package thesis.stocker.service;

import thesis.stocker.DTO.TransactionDTO;

public interface ITransactionService {

    TransactionDTO fetchById(int id);

    boolean transact(TransactionDTO transaction) throws Exception;

}
