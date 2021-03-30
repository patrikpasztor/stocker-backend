package thesis.stocker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
    public String buy(@RequestBody TransactionDTO transactionDTO) {
        try {
            System.out.println("vásárlás beérkezés: " + transactionDTO.getUser() + " " + transactionDTO.getStock() + " " + transactionDTO.getAmount() + " " + transactionDTO.getStockPrice());
            transactionService.buy(transactionDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "bought";
    }

    @PostMapping(path = "/sell")
    public String sell(@RequestBody TransactionDTO transactionDTO) {
        try {
            transactionService.sell(transactionDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "sold";
    }

}
