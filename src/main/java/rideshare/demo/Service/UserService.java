package rideshare.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import rideshare.demo.Entity.User;
import rideshare.demo.Repository.UserRepository;

import java.io.IOException;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> getUserByUsername(String username) {
        return Optional.ofNullable(userRepository.findByEmail(username));
    }


    public long saveUserPhoto(long id, MultipartFile file) {
        User user = userRepository.findByUserId(id);
        try {
            user.setPhoto(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        userRepository.save(user);
        return user.getuserId();
    }

    public byte[] getUserPhoto(long id) {

        return userRepository.findByUserId(id).getPhoto();
    }


    public User getUserPublicInfo(long id) {
        return userRepository.findByUserId(id);
    }


    @PreAuthorize("isAuthenticated()")
    public Double rateUser(Integer rate, long id) {
        User user = userRepository.findByUserId(id);
        double newRate;
        if (user.getRateAmount() == 0) {
            user.setRate((double) rate);
            user.setRateAmount(1L);
            newRate = rate;
        } else {
            newRate = ((user.getRate() * user.getRateAmount()) + rate) / (user.getRateAmount() + 1);
            user.setRate((newRate));
            user.setRateAmount(user.getRateAmount() + 1);
        }
        userRepository.save(user);
        return newRate;
    }

    public Long editUser(User editUserRequest, long id) {
        User user = userRepository.findByUserId(id);
        if(userRepository.findByEmail(editUserRequest.getEmail())!=null) {
            throw new Error();
        }
        user.setBirthDate(editUserRequest.getBirthDate());
        user.setCar(editUserRequest.getCar());
        user.setDescription(editUserRequest.getDescription());
        user.setFirstName(editUserRequest.getFirstName());
        user.setLastName(editUserRequest.getLastName());
        user.setPhone(editUserRequest.getPhone());
        return userRepository.save(user).getuserId();
    }

}

