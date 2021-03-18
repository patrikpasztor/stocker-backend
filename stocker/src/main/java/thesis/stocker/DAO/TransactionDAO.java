package thesis.stocker.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import thesis.stocker.DTO.TransactionDTO;

@Component
public class TransactionDAO implements ITransactionDAO {
    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public boolean save(TransactionDTO transactionDTO) throws Exception {
        transactionRepository.save(transactionDTO);
        return false;
    }
}
