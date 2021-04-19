package thesis.stocker.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import thesis.stocker.DAO.ConfirmationTokenRepository;
import thesis.stocker.model.ConfirmationToken;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;

    public void saveConfirmationToken(ConfirmationToken confirmationToken) {
        confirmationTokenRepository.save(confirmationToken);
    }

    public void deleteConfirmationToken(Long id){

        confirmationTokenRepository.deleteById(id);
    }

    public Optional<ConfirmationToken> findConfirmationTokenByToken(String token) {
        return confirmationTokenRepository.findByConfirmationToken(token);
    }
}