package rideshare.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rideshare.demo.Entity.Consts;
import rideshare.demo.Entity.NewPassenger;
import rideshare.demo.Entity.Ride;
import rideshare.demo.Repository.RideRepository;
import rideshare.demo.Service.EmailService;
import rideshare.demo.Service.RideService;
import rideshare.demo.Service.UserService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin(value = {"*"})
public class RideController {

    private final RideService rideService;
    private final RideRepository rideRepository;
    private final EmailService emailService;
    private final UserService userService;

    @Autowired
    public RideController(RideService rideService, RideRepository rideRepository, EmailService emailService, UserService userService) {
        this.rideService = rideService;
        this.rideRepository = rideRepository;
        this.emailService = emailService;
        this.userService = userService;
    }

    @RequestMapping(value = "/ride/add", method = RequestMethod.POST)
    public ResponseEntity<List<Ride>> add(@RequestBody Ride ride) {

        //ride.setDate(LocalDate.from(ride.getDate().toEpochDay().minus(Duration.ofHours(0))));
        if (rideService.add(ride).isPresent()) {
            return new ResponseEntity<>( HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/ride/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<List<Ride>> update(@RequestBody Ride ride, @PathVariable("id") long id) {

        if (rideService.update(id, ride).isPresent()) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/ride/{id}", method = RequestMethod.GET)
    public ResponseEntity<Ride> get(@PathVariable("id") long id) {
        Ride ret = rideRepository.findByRideId(id);
        return  new ResponseEntity<>(ret, HttpStatus.OK);

    }


    @RequestMapping(value = "/ride/search", method = RequestMethod.GET)
    public ResponseEntity<List<Ride>> search(
            @RequestParam Long from,
            @RequestParam Long to,
            @RequestParam @DateTimeFormat(pattern = Consts.DATE_SEARCH_PATTERN) Date date
    ) {
        return new ResponseEntity<>(rideService.search(from, to, date), HttpStatus.OK);
    }



    @RequestMapping(value = "/rides/user/{userId}", method = RequestMethod.GET)
    public ResponseEntity<List<Ride>> getUserRides(
            @PathVariable("userId") long userId
    ) {
        return new ResponseEntity<>(rideService.getUserRides(userId), HttpStatus.OK);
    }

    @RequestMapping(value = "ride/{rideId}/passenger", method = RequestMethod.POST)
    public ResponseEntity<Ride> addPassenger(
            @PathVariable("rideId") long rideId,
            @RequestBody NewPassenger newPassenger
    ) {

        Optional<Ride> ride = rideService.addPassenger(rideId, newPassenger.getPassengerId());
        if (ride.isPresent()) {
            Ride mailRide = rideRepository.findByRideId(rideId);
            emailService.sendMessageConcurrency(userService.getUserPublicInfo(newPassenger.getPassengerId()).getEmail(), "Let's Share a Ride Rezerwacja", "Zarezerwowano przejazd z "+mailRide.getFrom()+
                    " do "+mailRide.getTo()+", data wyjazdu:  "+mailRide.getDate());
            return new ResponseEntity<>(HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/{rideId}/passenger/{passengerId}", method = RequestMethod.DELETE)
    public ResponseEntity<List<Ride>> removePassenger(
            @PathVariable("rideId") long rideId,
            @PathVariable("passengerId") long passengerId
    ) {
        rideService.removePassenger(rideId, passengerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping(value = "/ride/{id}")
    public ResponseEntity<String> deleteRide(@PathVariable("id") long id) {
        Ride ride = rideRepository.findByRideId(id);
        for(int i=0;i<ride.getPassengers().size();i++){
            emailService.sendMessageConcurrency(userService.getUserPublicInfo(ride.getPassengers().get(i)).getEmail(), "Let's Share a Ride", "Przepraszamy, przejazd z "
                    +ride.getFrom()+" do "+ride.getTo()+" został odwołany przez kierowce.");
        }
        rideService.deleteRide(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/ride/all", method = RequestMethod.GET)
    public ResponseEntity<List<Ride>> list() {
        return new ResponseEntity<>(rideService.getAll(), HttpStatus.OK);
    }

}
