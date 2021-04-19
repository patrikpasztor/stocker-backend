package thesis.stocker.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import thesis.stocker.model.User;

import java.text.MessageFormat;
import java.util.Map;
import java.util.Optional;

@Component
public class UserDAO implements IUserDAO{

    @Autowired
    UserRepository userRepository;

    @Override
    public User save(User user) throws Exception {
        userRepository.save(user);
        return user;
    }

    @Override
    public User findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public Double getStockAmount(String email, String stock) {
        User user = findByEmail(email);
        Map<String, Double> stockAmountMap = user.getStockAmountMap();
        if(stockAmountMap.containsKey(stock)) {
            return stockAmountMap.get(stock);
        }
        else {
            return (double) 0;
        }
    }

    @Override
    public User findByEmail(String email) throws UsernameNotFoundException {
        final Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        else {
            throw new UsernameNotFoundException(MessageFormat.format("User with email {0} cannot be found.", email));
        }
    }
}
