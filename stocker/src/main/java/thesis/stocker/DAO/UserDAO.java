package thesis.stocker.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import thesis.stocker.model.User;

import java.util.Map;

@Component
public class UserDAO implements IUserDAO{

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean save(User user) throws Exception {
        userRepository.save(user);
        return false;
    }

    @Override
    public User findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public Double getStockAmount(String name, String stock) {
        User user = findByName(name);
        Map<String, Double> stockAmountMap = user.getStockAmountMap();
        if(stockAmountMap.containsKey(stock)) {
            return stockAmountMap.get(stock);
        }
        else {
            return (double) 0;
        }
    }
}
