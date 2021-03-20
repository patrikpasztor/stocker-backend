package thesis.stocker.service;

import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import thesis.stocker.DAO.ITransactionDAO;
import thesis.stocker.DTO.TransactionDTO;
import thesis.stocker.model.Transaction;

@Component
public class TransactionService implements ITransactionService {

    @Autowired
    ITransactionDAO transactionDAO;

    @Autowired
    MapperService mapperService;

    @Autowired
    IUserService userService;

    @Override
    public TransactionDTO fetchById(int id) {
        return null;
    }

    @Override
    public boolean buy(TransactionDTO transactionDTO) throws Exception {
        Transaction transaction = mapperService.dtoToTransaction(transactionDTO);
        transaction.setType("buy");
        transactionDAO.save(transaction);

        userService.updateBuy(transactionDTO);

        return false;
    }

    @Override
    public boolean sell(TransactionDTO transactionDTO) throws Exception {
        Transaction transaction = mapperService.dtoToTransaction(transactionDTO);
        transaction.setType("sell");
        transactionDAO.save(transaction);

        userService.updateSell(transactionDTO);

        return false;
    }
}
