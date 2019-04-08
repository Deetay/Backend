package rideshare.demo.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import rideshare.demo.Entity.User;
import rideshare.demo.Repository.UserRepository;
import rideshare.demo.Service.EmailService;
import rideshare.demo.Service.UserService;
import rideshare.demo.security.JwtTokenUtil;


import javax.naming.AuthenticationException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(value = {"*"})
public class UserController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserService userService;
    private final EmailService emailService;

    @Autowired
    public UserController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, UserService userService, EmailService emailService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.emailService = emailService;
    }

    @PostMapping("/signup")
    public User signUp(@RequestBody User signUpUserRequest) {
        signUpUserRequest.setPassword(passwordEncoder.encode(signUpUserRequest.getPassword()));
        userRepository.save(signUpUserRequest);
        emailService.sendMessageConcurrency(signUpUserRequest.getEmail(),"Let's Share a Ride", "Witaj, "+signUpUserRequest.getFirstName()+"\n"+"Dziekujemy za rejestracje w naszym serwisie.\nZaloguj sie do serwisu, aby móc dodawać i rezerwować przejazdy.");
        return signUpUserRequest;
    }
    @GetMapping("/user/all")
    public List<User> get() {

        return userRepository.findAll();
    }

    @PostMapping(value = "/user/authenticate")
    public ResponseEntity signIn(@RequestBody User signingUser) throws AuthenticationException {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signingUser.getEmail(),
                        signingUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final User user = userRepository.findByEmail(signingUser.getEmail());
        final String token = jwtTokenUtil.generateToken(user);
        Map<String, Object> userMap = new HashMap<String, Object>(){{put("user", user);put("token", token);}};

        return ResponseEntity.ok(userMap);
    }

    @GetMapping(value = "/user/{id}")
    public ResponseEntity getPublicInfo(@PathVariable("id") long id) {
       // User user = userRepository.findByUserId(id);
        return ResponseEntity.ok(userService.getUserPublicInfo(id));
    }

    @PutMapping(value = "/user/edit/{id}")
    public ResponseEntity<?> editUser(@PathVariable("id") long id, @RequestBody User userRequest) {
        return new ResponseEntity<>(userService.editUser(userRequest, id), HttpStatus.CREATED);
    }

    @PutMapping(value = "/user/rate/{id}")
    public ResponseEntity<Double> rateUser(@PathVariable("id") long id,@RequestParam Integer rate) throws IOException {
        return new ResponseEntity<>(userService.rateUser(rate, id), HttpStatus.OK);
    }

    @PostMapping(value = "/user/addphoto/{id}")
    public ResponseEntity<?> photoUser(@PathVariable("id") long id, @RequestParam("file") MultipartFile file) {

        return new ResponseEntity<>(userService.saveUserPhoto(id, file), HttpStatus.OK);
    }
    @GetMapping(value = "/user/photo/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable("id") long id) throws IOException {
        return new ResponseEntity<>(userService.getUserPhoto(id), HttpStatus.OK);
    }

}
