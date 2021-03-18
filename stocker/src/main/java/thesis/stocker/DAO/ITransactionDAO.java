package thesis.stocker.DAO;

import thesis.stocker.DTO.TransactionDTO;

public interface ITransactionDAO {
    public boolean save(TransactionDTO transactionDTO) throws Exception;
}
