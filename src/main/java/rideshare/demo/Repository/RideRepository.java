package rideshare.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rideshare.demo.Entity.Ride;

public interface RideRepository extends JpaRepository<Ride, Long> {

}
