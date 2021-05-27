package thesis.stocker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import thesis.stocker.DTO.TransactionDTO;
import thesis.stocker.service.ITransactionService;


@RestController()
public class TransactionController {

    @Autowired
    private ITransactionService transactionService;

    @PostMapping(path = "/buy")
    public boolean buy(@RequestBody TransactionDTO transactionDTO) {
        try {
            System.out.println("vásárlás beérkezés: " + transactionDTO.getUser() + " " + transactionDTO.getStock() + " " + transactionDTO.getAmount() + " " + transactionDTO.getStockPrice());
            return transactionService.buy(transactionDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @PostMapping(path = "/sell")
    public boolean sell(@RequestBody TransactionDTO transactionDTO) {
        try {
            return transactionService.sell(transactionDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
