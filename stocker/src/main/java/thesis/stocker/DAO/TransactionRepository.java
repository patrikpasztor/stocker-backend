package thesis.stocker.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import thesis.stocker.DTO.TransactionDTO;

public interface TransactionRepository extends JpaRepository<TransactionDTO, Integer> {
}
