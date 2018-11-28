package rideshare.demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Repos extends JpaRepository<Car, Long> {
    Car findByCarId(Long id);
}
