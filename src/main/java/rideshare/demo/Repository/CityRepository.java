package rideshare.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rideshare.demo.Entity.City;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> {
    List<City> findAllByOrderByCityId();

}
