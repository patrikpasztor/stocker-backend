package thesis.stocker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import thesis.stocker.DAO.ITransactionDAO;
import thesis.stocker.DTO.TransactionDTO;

@Component
public class TransactionService implements ITransactionService {

    @Autowired
    ITransactionDAO transactionDAO;

    @Override
    public TransactionDTO fetchById(int id) {
        return null;
    }

    @Override
    public boolean transact(TransactionDTO transaction) throws Exception {
        transactionDAO.save(transaction);
        return false;
    }
}
