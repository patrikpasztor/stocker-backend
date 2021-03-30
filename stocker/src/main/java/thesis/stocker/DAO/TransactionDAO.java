package thesis.stocker.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import thesis.stocker.model.Transaction;
import thesis.stocker.model.User;

import java.util.List;

@Component
public class TransactionDAO implements ITransactionDAO {
    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public boolean save(Transaction transaction) throws Exception {
        transactionRepository.save(transaction);
        return false;
    }

    @Override
    public Double getAverageBuyPrice(User user, String stock) throws Exception {
        double sumPrice = 0;
        double sumAmount = 0;
        List<Transaction> stockPurchases = transactionRepository.findByStockAndTypeAndUser(stock, "buy", user);
        for(Transaction tran : stockPurchases) {
            sumPrice += tran.getStockPrice() * tran.getAmount();
            sumAmount += tran.getAmount();
        }
        return sumPrice / sumAmount;
    }
}
