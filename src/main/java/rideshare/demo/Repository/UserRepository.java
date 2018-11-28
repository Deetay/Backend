package rideshare.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rideshare.demo.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserId(Long Id);
    User findByEmail(String email);
}
