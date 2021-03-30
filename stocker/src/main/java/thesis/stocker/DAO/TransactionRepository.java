package thesis.stocker.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import thesis.stocker.model.Transaction;
import thesis.stocker.model.User;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    List<Transaction> findByStockAndTypeAndUser(String stock, String type, User user);

}
