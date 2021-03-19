package thesis.stocker.DAO;

import thesis.stocker.model.Transaction;

public interface ITransactionDAO {
    public boolean save(Transaction transaction) throws Exception;
}
