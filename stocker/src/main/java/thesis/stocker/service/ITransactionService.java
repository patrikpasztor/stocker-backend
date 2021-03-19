package thesis.stocker.service;

import thesis.stocker.DTO.TransactionDTO;

import java.util.List;

public interface ITransactionService {

    TransactionDTO fetchById(int id);

    boolean buy(TransactionDTO transaction) throws Exception;

    boolean sell(TransactionDTO transaction) throws Exception;

}
