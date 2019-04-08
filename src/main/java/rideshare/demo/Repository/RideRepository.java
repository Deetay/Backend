package rideshare.demo.Repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rideshare.demo.Entity.Ride;

import java.util.Date;
import java.util.List;

public interface RideRepository extends JpaRepository<Ride, Long> {

    @Query(
            "select distinct r from Ride r "
                    + "left join r.from f "
                    + "left join r.to t "
                    + "WHERE " +
                    "(r.date > :dateStart AND f.city.cityId = :cityFrom AND t.city.cityId = :cityTo) "
    )
    List<Ride> findRoutesDateFromTo(
            @Param(value = "dateStart") Date dateStart,
            @Param(value = "cityFrom") Long cityFrom,
            @Param(value = "cityTo") Long cityTo
    );


    List<Ride> findByOwnerId(long id);

    Ride findByRideId(Long id);

    Integer countByOwnerId(long id);
}
