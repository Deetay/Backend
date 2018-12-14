package rideshare.demo.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rideshare.demo.Entity.User;
import rideshare.demo.Repository.UserRepository;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private UserRepository userRepository;
    @Autowired
    public UserDetailsService(UserRepository userRepository) {
        this.userRepository=userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) throw new UsernameNotFoundException(email + " not found");
        return new UserDetails(user);
    }
}