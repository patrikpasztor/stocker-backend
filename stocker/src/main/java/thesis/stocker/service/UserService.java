package thesis.stocker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import thesis.stocker.DAO.ITransactionDAO;
import thesis.stocker.DAO.IUserDAO;
import thesis.stocker.DTO.StockDTO;
import thesis.stocker.DTO.TransactionDTO;
import thesis.stocker.DTO.UserDTO;
import thesis.stocker.model.ConfirmationToken;
import thesis.stocker.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserService  implements IUserService, UserDetailsService {

    @Autowired
    IUserDAO userDAO;

    @Autowired
    ITransactionDAO transactionDAO;

    @Autowired
    MapperService mapperService;

    @Autowired
    ConfirmationTokenService confirmationTokenService;

    @Autowired
    EmailSenderService emailSenderService;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

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
        return true;
    }

    @Override
    public boolean updateBuy(TransactionDTO transactionDTO) {
        String stock = transactionDTO.getStock();
        Double addition = transactionDTO.getAmount();
        Double sumPrice = transactionDTO.getStockPrice() * transactionDTO.getAmount();
        User user = userDAO.findByEmail(transactionDTO.getUser());
        System.out.println("neki veszunk: " + user.getName());

        Double ownedAmount = getStockAmount(transactionDTO.getUser(), stock);
        Double currentBalance = user.getBalance();
        user.setStockAmount(stock, ownedAmount + addition);
        user.setBalance(currentBalance - sumPrice);

        try {
            save(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public boolean updateSell(TransactionDTO transactionDTO) {
        String stock = transactionDTO.getStock();
        Double extraction = transactionDTO.getAmount();
        User user = userDAO.findByEmail(transactionDTO.getUser());
        System.out.println("tole adunk el: " + user.getName());
        Double ownedAmount = getStockAmount(transactionDTO.getUser(), stock);
        Double newAmount = ownedAmount - extraction;
        Double sumPrice = transactionDTO.getStockPrice() * transactionDTO.getAmount();
        Double currentBalance = user.getBalance();
        user.setBalance(currentBalance + sumPrice);

        if(newAmount.equals(0d))
            user.deleteStock(stock);
        else
            user.setStockAmount(stock, newAmount);
        try {
            save(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public Double getStockAmount(String email, String stock) {
        return userDAO.getStockAmount(email, stock);
    }

    @Override
    public List<StockDTO> listOwnedStocks(String email) {
        try{
            User user= userDAO.findByEmail(email);
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
    public Double getAverageBuyPrice(String email, String stock) {
        try {
            User user = userDAO.findByEmail(email);
            return transactionDAO.getAverageBuyPrice(user, stock);
        } catch (Exception e) {
            e.printStackTrace();
            return 0d;
        }
    }

    @Override
    public void signUpUser(User user) {
        System.out.println("Már a userservice-ben pw: " + user.getPassword());
        final String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);

        try {
            final User createdUser = userDAO.save(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

        final ConfirmationToken confirmationToken = new ConfirmationToken(user);

        confirmationTokenService.saveConfirmationToken(confirmationToken);

        sendConfirmationMail(user.getEmail(), confirmationToken.getConfirmationToken());
    }

    @Override
    public void confirmUser(ConfirmationToken confirmationToken) {
        final User user = confirmationToken.getUser();

        user.setEnabled(true);

        try {
            userDAO.save(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

        confirmationTokenService.deleteConfirmationToken(confirmationToken.getId());
    }

    @Override
    public void sendConfirmationMail(String userMail, String token) {
        System.out.println("Editing email to send: " + userMail + token);
        final SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(userMail);
        mailMessage.setSubject("Stocker Confirmation!");
        mailMessage.setFrom("parasnorik@gmail.com");
        mailMessage.setText(
                "Thank you for registering. Please click on the below link to activate your account." + "http://localhost:9090/api/confirm?token="
                        + token);


        emailSenderService.sendEmail(mailMessage);
    }

    @Override
    public boolean authenticate(String email, String password) {
        User user = userDAO.findByEmail(email);
        System.out.println("authenticate: " + password  + " " + user.getPassword());
        return bCryptPasswordEncoder.matches(password, user.getPassword()) && user.getEnabled();
    }

    @Override
    public Double chargeBalance(String email, Double amount) {
        User user = userDAO.findByEmail(email);
        Double newBalance = user.getBalance() + amount;
        user.setBalance(newBalance);
        try {
            userDAO.save(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newBalance;
    }

    @Override
    public Double getBalance(String email) {
        User user = userDAO.findByEmail(email);
        return user.getBalance();
    }

    @Override
    public Boolean watchStock(String email, String stock) {
        try {
            User user = userDAO.findByEmail(email);
            List<String> watchlist = user.getWatchlist();
            watchlist.add(stock);
            user.setWatchlist(watchlist);
            userDAO.save(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean stopWatchStock(String email, String stock) {
        try {
            User user = userDAO.findByEmail(email);
            List<String> watchlist = user.getWatchlist();
            watchlist.remove(stock);
            user.setWatchlist(watchlist);
            userDAO.save(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<String> getWatchlistStocks(String email) {
        try{
            User user= userDAO.findByEmail(email);
            return user.getWatchlist();
        } catch(NullPointerException e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
