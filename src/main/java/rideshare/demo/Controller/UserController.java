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
import rideshare.demo.Entity.User;
import rideshare.demo.Repository.UserRepository;
import rideshare.demo.security.JwtTokenUtil;


import javax.naming.AuthenticationException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(value = {"*"})
public class UserController {
    @Autowired
    private final AuthenticationManager authenticationManager;
    @Autowired
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/signup")
    public User signUp(@RequestBody User signUpUserRequest) {
        signUpUserRequest.setPassword(passwordEncoder.encode(signUpUserRequest.getPassword()));
        userRepository.save(signUpUserRequest);
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

}
