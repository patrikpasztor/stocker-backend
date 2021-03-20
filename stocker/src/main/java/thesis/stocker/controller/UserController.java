package thesis.stocker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import thesis.stocker.DTO.UserDTO;
import thesis.stocker.model.User;
import thesis.stocker.service.IUserService;
import thesis.stocker.service.MapperService;

import java.util.HashMap;
import java.util.Map;

@RestController
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

}
