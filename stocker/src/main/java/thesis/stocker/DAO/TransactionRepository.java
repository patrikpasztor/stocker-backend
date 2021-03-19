package thesis.stocker.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import thesis.stocker.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
}
