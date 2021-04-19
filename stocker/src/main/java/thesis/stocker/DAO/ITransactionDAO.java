package thesis.stocker.DAO;

import thesis.stocker.model.Transaction;
import thesis.stocker.model.User;

public interface ITransactionDAO {
    boolean save(Transaction transaction) throws Exception;

    Double getAverageBuyPrice(User user, String stock) throws Exception;
}
