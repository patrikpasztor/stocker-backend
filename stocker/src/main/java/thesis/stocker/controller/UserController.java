package thesis.stocker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import thesis.stocker.DTO.UserDTO;
import thesis.stocker.service.IUserService;

@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping(path="/saveuser", consumes = "application/json")
    public String saveUser(@RequestBody UserDTO user){
        try {
            userService.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "start";
    }

}
