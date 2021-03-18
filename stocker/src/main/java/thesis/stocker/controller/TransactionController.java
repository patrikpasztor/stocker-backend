package thesis.stocker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import thesis.stocker.DTO.TransactionDTO;
import thesis.stocker.service.ITransactionService;

@RestController
public class TransactionController {

    @Autowired
    private ITransactionService transactionService;

    @GetMapping(path = "/transact")
    public String transact(@RequestBody TransactionDTO transactionDTO) {
        try {
            transactionService.transact(transactionDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "gotcha";
    }

}
