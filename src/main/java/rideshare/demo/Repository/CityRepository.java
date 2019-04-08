package rideshare.demo.Repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rideshare.demo.Entity.City;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> {

    City getCityByCityId(Long id);
    List<City> findAllByOrderByCityId();

    @Query("select c from City c WHERE UPPER(c.name) LIKE CONCAT(UPPER(:countryName),'%') ORDER BY c.population DESC ")
    List<City> findCities(@Param(value = "countryName") String countryName, Pageable pageable);
}
