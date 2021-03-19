package thesis.stocker.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import thesis.stocker.DTO.TransactionDTO;
import thesis.stocker.model.Transaction;

@Component
public class TransactionDAO implements ITransactionDAO {
    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public boolean save(Transaction transaction) throws Exception {
        transactionRepository.save(transaction);
        return false;
    }
}
