package thesis.stocker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import thesis.stocker.DAO.ITransactionDAO;
import thesis.stocker.DAO.IUserDAO;
import thesis.stocker.DTO.StockDTO;
import thesis.stocker.DTO.TransactionDTO;
import thesis.stocker.DTO.UserDTO;
import thesis.stocker.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserService  implements IUserService{

    @Autowired
    IUserDAO userDAO;

    @Autowired
    ITransactionDAO transactionDAO;

    @Autowired
    MapperService mapperService;

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public User findByName(String name) {
        return userDAO.findByName(name);
    }

    @Override
    public boolean save(User user) throws Exception {
        userDAO.save(user);
        return false;
    }

    @Override
    public List<UserDTO> findAll() throws Exception {
        return null;
    }

    @Override
    public boolean updateBuy(TransactionDTO transactionDTO) {
        String stock = transactionDTO.getStock();
        Double addition = transactionDTO.getAmount();
        User toUpdate = findByName(transactionDTO.getUser());
        System.out.println("neki veszunk: " + toUpdate.getName());

        Double ownedAmount = getStockAmount(transactionDTO.getUser(), stock);
        toUpdate.setStockAmount(stock, ownedAmount + addition);

        try {
            save(toUpdate);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean updateSell(TransactionDTO transactionDTO) {
        String stock = transactionDTO.getStock();
        Double extraction = transactionDTO.getAmount();
        User toUpdate = findByName(transactionDTO.getUser());
        System.out.println("tole adunk el: " + toUpdate.getName());

        Double ownedAmount = getStockAmount(transactionDTO.getUser(), stock);
        toUpdate.setStockAmount(stock, ownedAmount - extraction);

        try {
            save(toUpdate);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Double getStockAmount(String name, String stock) {
        return userDAO.getStockAmount(name, stock);
    }

    @Override
    public List<StockDTO> listOwnedStocks(String username) {
        try{
            User user= userDAO.findByName(username);
            Map<String,Double> ownedMap = user.getStockAmountMap();
            List<StockDTO> ownedList = new ArrayList<>();
            for(Map.Entry<String,Double> entry : ownedMap.entrySet()) {
                ownedList.add(new StockDTO(entry.getKey(), entry.getValue()));
            }
            return ownedList;
        } catch(NullPointerException e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Double getAverageBuyPrice(String username, String stock) {
        try {
            User user = userDAO.findByName(username);
            return transactionDAO.getAverageBuyPrice(user, stock);
        } catch (Exception e) {
            e.printStackTrace();
            return 0d;
        }
    }

}
