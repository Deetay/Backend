package rideshare.demo.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rideshare.demo.Entity.User;
import rideshare.demo.Repository.UserRepository;


import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(value = {"*"})
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/signup")
    public User signUp(@RequestBody User signUpUserRequest) {
        userRepository.save(signUpUserRequest);

        return signUpUserRequest;
    }
    @GetMapping("/user/all")
    public List<User> get() {

        return userRepository.findAll();
    }

}
