package thesis.stocker.service;

import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import thesis.stocker.DAO.ITransactionDAO;
import thesis.stocker.DAO.IUserDAO;
import thesis.stocker.DTO.TransactionDTO;
import thesis.stocker.model.Transaction;
import thesis.stocker.model.User;

@Component
public class TransactionService implements ITransactionService {

    @Autowired
    ITransactionDAO transactionDAO;

    @Autowired
    IUserDAO userDAO;

    @Autowired
    MapperService mapperService;

    @Autowired
    IUserService userService;

    @Override
    public TransactionDTO fetchById(int id) {
        return null;
    }

    @Override
    public boolean buy(TransactionDTO transactionDTO) {
        Transaction transaction = mapperService.dtoToTransaction(transactionDTO);
        transaction.setType("buy");
        User user = userDAO.findByEmail(transactionDTO.getUser());
        Double sumPrice = transaction.getStockPrice() * transaction.getAmount();
        if(user.getBalance() >= sumPrice) {
            try {
                transactionDAO.save(transaction);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            userService.updateBuy(transactionDTO);
            return true;
        } else {
            System.out.println("Nincs elég pénz");
            return false;
        }
    }

    @Override
    public boolean sell(TransactionDTO transactionDTO) {
        Transaction transaction = mapperService.dtoToTransaction(transactionDTO);
        transaction.setType("sell");
        try {
            transactionDAO.save(transaction);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        userService.updateSell(transactionDTO);

        return true;
    }
}
