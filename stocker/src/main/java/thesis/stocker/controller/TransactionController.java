package thesis.stocker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import thesis.stocker.DTO.TransactionDTO;
import thesis.stocker.DTO.UserDTO;
import thesis.stocker.service.ITransactionService;
import thesis.stocker.service.IUserService;

@RestController
public class TransactionController {

    @Autowired
    private ITransactionService transactionService;

    @Autowired
    private IUserService userService;

    @GetMapping(path = "/buy")
    public String buy(@RequestBody TransactionDTO transactionDTO) {
        try {
            UserDTO testUser1 = new UserDTO("paspat", 10000000);
            UserDTO testUser2 = new UserDTO("asdasd", 300);
            userService.save(testUser1);
            userService.save(testUser2);
            transactionService.buy(transactionDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "gotcha";
    }

}
