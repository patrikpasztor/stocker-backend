package thesis.stocker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thesis.stocker.DTO.StockDTO;
import thesis.stocker.DTO.UserDTO;
import thesis.stocker.model.User;
import thesis.stocker.service.IUserService;
import thesis.stocker.service.MapperService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController()
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    MapperService mapperService;

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

    @GetMapping(path="/listStocks/{user}", produces="application/json")
    public ResponseEntity<List<StockDTO>> listOwnedStocks(@PathVariable String user) {
        try{
            return new ResponseEntity<>(userService.listOwnedStocks(user), HttpStatus.OK) ;
        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

    }

    @GetMapping(path="/averageBuyPrice/{user}/{stock}")
    public ResponseEntity<Double> getAverageBuyPrice(@PathVariable String user, @PathVariable String stock) {
        try{
            return new ResponseEntity<>(userService.getAverageBuyPrice(user, stock), HttpStatus.OK) ;
        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

}
