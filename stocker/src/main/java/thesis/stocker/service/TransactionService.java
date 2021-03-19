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
    private MapperService mapperService;

    @Override
    public TransactionDTO fetchById(int id) {
        return null;
    }

    @Override
    public boolean buy(TransactionDTO transactionDTO) throws Exception {
        Transaction transaction = mapperService.dtoToTransaction(transactionDTO);
        transaction.setType("buy");
        transactionDAO.save(transaction);
        return false;
    }

    @Override
    public boolean sell(TransactionDTO transaction) throws Exception {
        return false;
    }
}
