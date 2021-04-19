package thesis.stocker.DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import thesis.stocker.model.ConfirmationToken;

import java.util.Optional;

@Repository
public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, Long> {

    Optional<ConfirmationToken> findByConfirmationToken(String token);
}