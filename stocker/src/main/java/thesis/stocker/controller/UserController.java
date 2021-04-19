package thesis.stocker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thesis.stocker.DTO.*;
import thesis.stocker.model.ConfirmationToken;
import thesis.stocker.model.User;
import thesis.stocker.service.ConfirmationTokenService;
import thesis.stocker.service.IUserService;
import thesis.stocker.service.MapperService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin("*")
@RestController()
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    MapperService mapperService;

    @Autowired
    ConfirmationTokenService confirmationTokenService;

    @GetMapping(path="/saveuser", consumes = "application/json")
    public String saveUser(@RequestBody UserDTO userDTO){
        try {
            User user = mapperService.dtoToUser(userDTO);
            userService.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "start";
    }

    @GetMapping(path="/fill")
    public String fillTestUsers() {
        try {
            User testUser1 = new User("paspat");
            User testUser2 = new User("asdasd");
            Map<String, Double> testMap1 = new HashMap<>();
            Map<String, Double> testMap2 = new HashMap<>();
            testMap1.put("TSLA", 20D);
            testUser1.setStockAmountMap(testMap1);
            testUser2.setStockAmountMap(testMap2);
            userService.save(testUser1);
            userService.save(testUser2);
            return "saved";
        }
        catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @GetMapping(path="/listStocks/{email}", produces="application/json")
    public ResponseEntity<List<StockDTO>> listOwnedStocks(@PathVariable String email) {
        try{
            return new ResponseEntity<>(userService.listOwnedStocks(email), HttpStatus.OK) ;
        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

    }

    @GetMapping(path="/averageBuyPrice/{email}/{stock}")
    public ResponseEntity<Double> getAverageBuyPrice(@PathVariable String email, @PathVariable String stock) {
        try{
            return new ResponseEntity<>(userService.getAverageBuyPrice(email, stock), HttpStatus.OK) ;
        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/sign-up")
    public String signUp(@RequestBody UserDTO userDTO) {
        System.out.println("pw: " + userDTO.getPassword());
        System.out.println("name: " + userDTO.getName());
        System.out.println("email: " + userDTO.getEmail());
        User user = mapperService.dtoToUser(userDTO);
        userService.signUpUser(user);

        return "signed-up";
    }

    @GetMapping("/confirm")
    public String confirmMail(@RequestParam("token") String token) {

        Optional<ConfirmationToken> optionalConfirmationToken = confirmationTokenService.findConfirmationTokenByToken(token);

        optionalConfirmationToken.ifPresent(userService::confirmUser);

        return "confirmed";
    }

    @PostMapping(path = "/auth", consumes = "application/json")
    public boolean authenticate(@RequestBody AuthDTO authDTO){
        return userService.authenticate(authDTO.getEmail(), authDTO.getPassword());
    }

    @PostMapping(path = "/charge-balance", consumes = "application/json")
    public Double chargeBalance(@RequestBody BalanceChargeDTO balanceChargeDTO) {
        return userService.chargeBalance(balanceChargeDTO.getEmail(), balanceChargeDTO.getAmount());
    }

    @GetMapping("/balance/{email}")
    public ResponseEntity<Double> getBalance(@PathVariable String email) {
        try{
            return new ResponseEntity<>(userService.getBalance(email), HttpStatus.OK) ;
        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/watch", consumes = "application/json")
    public ResponseEntity<Boolean> watchStock(@RequestBody WatchDTO watchDTO) {
        try{
            System.out.println(watchDTO.getEmail());
            return new ResponseEntity<>(userService.watchStock(watchDTO.getEmail(),watchDTO.getStock()), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/stop-watch", consumes = "application/json")
    public ResponseEntity<Boolean> stopWatchStock(@RequestBody WatchDTO watchDTO) {
        try{
            return new ResponseEntity<>(userService.stopWatchStock(watchDTO.getEmail(),watchDTO.getStock()), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path="/watchlist/{email}", produces="application/json")
    public ResponseEntity<List<String>> getWatchlistStocks(@PathVariable String email) {
        try{
            return new ResponseEntity<>(userService.getWatchlistStocks(email), HttpStatus.OK) ;
        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

    }
}
