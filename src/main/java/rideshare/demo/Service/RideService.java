package rideshare.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rideshare.demo.Entity.City;
import rideshare.demo.Entity.Place;
import rideshare.demo.Entity.Ride;
import rideshare.demo.Repository.CityRepository;
import rideshare.demo.Repository.RideRepository;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RideService {

    private final RideRepository rideRepository;
    private final CityService cityService;
    private final UserService userService;
    private final CityRepository cityRepository;

    @Autowired
    public RideService(RideRepository rideRepository, CityService cityService, UserService userService, CityRepository cityRepository)
    {
        this.rideRepository = rideRepository;
        this.cityService = cityService;
        this.userService = userService;
        this.cityRepository = cityRepository;
    }

    public List<Ride> getAll() {
        return rideRepository.findAll();
    }
    //works
    public Optional<Ride> add(Ride ride) {
        Optional<City> fromCity = cityService.get(ride.getFrom().getCity().getCityId());
        Optional<City> toCity = cityService.get(ride.getTo().getCity().getCityId());

        if (fromCity.isPresent() && toCity.isPresent()) {
            ride.getFrom().setCity(fromCity.get());
            ride.getTo().setCity(toCity.get());
            ride.setPassengers(new ArrayList<>());

            ride = rideRepository.save(ride);
            return Optional.of(ride);
        }
        return Optional.empty();
    }

//    @PostConstruct
//    public void tryAddRide(){
//
//
//        City c1 = cityRepository.getCityByCityId(Long.parseLong("1"));
//        City c2 = cityRepository.getCityByCityId(Long.parseLong("2"));
//        Place to = new Place();
//        Place from = new Place();
//        from.setCity(c1);
//        to.setCity(c2);
//        Ride r1 = new Ride();
//        r1.setbookedSeats(2);
//        r1.setDate(Date.from(Instant.now()));
//        r1.setFrom(from);
//        r1.setTo(to);
//        add(r1);
//
//        System.out.println("yikes");
//    }


    public List<Ride> userRoute(long id) {
        return rideRepository.findByOwnerId(id);
    }
    //works like a charm
    public List<Ride> search(Long from, Long to, Date date) {
        List<Ride> result = Collections.emptyList();
        try {

                Date fromDate = Date.from(date.toInstant());
                Date toDate = Date.from(date.toInstant().plus(Duration.ofHours(24)));
                result = rideRepository.findRoutesDateFromTo(fromDate, from, to);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }



    public Optional<Ride> get(long id) {
        return Optional.ofNullable(rideRepository.findByRideId(id));
    }

    public Optional<Ride> update(long id, Ride ride) {
        Ride rideUpdate = rideRepository.findByRideId(id);
        if (rideUpdate != null) {
            try {
                modifyRoute(ride, rideUpdate);
                return Optional.of(rideRepository.save(rideUpdate));
            } catch (Error e) {
                e.printStackTrace();
            }
        }
        return Optional.empty();
    }

    private void modifyRoute(Ride ride, Ride rideUpdate) {

        if (ride.getbookedSeats() != null) {
            rideUpdate.setbookedSeats(ride.getbookedSeats());
        }
        if (ride.getNumberOfSeats() != null) {
            rideUpdate.setNumberOfSeats(ride.getNumberOfSeats());
        }
        if (ride.getPrice() != null) {
            rideUpdate.setPrice(ride.getPrice());
        }
        modifyLocalization(ride.getFrom(), rideUpdate.getFrom());
        modifyLocalization(ride.getTo(), rideUpdate.getTo());
    }

    private void modifyLocalization(Place newLocalization, Place place){
        if (newLocalization != null) {
            if (newLocalization.getPlaceInfo() != null) {
                place.setPlaceInfo(newLocalization.getPlaceInfo());
            }
            if (newLocalization.getCity() != null && newLocalization.getCity().getCityId() != null) {
                Optional<City> newCity = cityService.get(newLocalization.getCity().getCityId());
                if (newCity.isPresent()) {
                    place.setCity(newCity.get());
                } else {
                    System.out.print("Blad miasta");
                }
            }
        }
    }

    public void removeAll() {
        rideRepository.deleteAll();
    }


    public Integer countUserRoute(long id) {
        return rideRepository.countByOwnerId(id);
    }
    
    public Optional<Ride> addPassenger(long rideId, Long newPassenger) {
        Ride rideUpdate = rideRepository.findByRideId(rideId);

        if (rideUpdate != null && newPassenger != null && userService.getUserPublicInfo(newPassenger) != null) {
            if (rideUpdate.getNumberOfSeats() > rideUpdate.getbookedSeats()) {
                rideUpdate.setbookedSeats(rideUpdate.getbookedSeats() + 1);
                rideUpdate.getPassengers().add(newPassenger);
                Ride ride = rideRepository.save(rideUpdate);
                return Optional.of(ride);
            }
        }
        return Optional.empty();
    }
    
    public void removePassenger(long rideId, long passengerId) {
        Ride rideUpdate = rideRepository.findByRideId(rideId);

        if (rideUpdate != null) {
            rideUpdate.setPassengers(
                    rideUpdate.getPassengers().stream()
                            .filter(p -> !p.equals(passengerId))
                            .collect(Collectors.toList())
            );
            rideUpdate.setbookedSeats(rideUpdate.getbookedSeats()-1);
            Ride ride = rideRepository.save(rideUpdate);
        }
    }
    
    public List<Ride> getUserRides(long userId) {
        return rideRepository.findByOwnerId(userId);
    }

    public void deleteRide(long id) {
        rideRepository.deleteById(id);
    }
}
