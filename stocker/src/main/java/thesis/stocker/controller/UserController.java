package thesis.stocker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import thesis.stocker.DTO.UserDTO;
import thesis.stocker.service.IUserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value="/saveuser")
    public String saveUser(UserDTO userDTO){
        userDTO.setName("paspat");
        try {
            userService.save(userDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "start";
    }

}
