package thesis.stocker.DAO;

import thesis.stocker.model.Transaction;
import thesis.stocker.model.User;

import java.util.List;

public interface ITransactionDAO {
    boolean save(Transaction transaction) throws Exception;

    Double getAverageBuyPrice(User user, String stock) throws Exception;
}
