package rideshare.demo.Entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Ride {
    private Long routeId;
    private Long ownerId;
    private String description;
    private Place from;
    private Place destination;
    private Integer numberOfSeats;
    private Integer bookedSeats;
    private Double price;
    private List<Long> passengers;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }


    @ManyToOne(cascade = CascadeType.ALL)
    public Place getFrom() {
        return from;
    }

    public void setFrom(Place from) {
        this.from = from;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    public Place getDestination() {
        return destination;
    }

    public void setDestination(Place destination) {
        this.destination = destination;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getbookedSeats() {
        return bookedSeats;
    }

    public void setbookedSeats(Integer bookedSeats) {
        this.bookedSeats = bookedSeats;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ElementCollection
    public List<Long> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Long> passengers) {
        this.passengers = passengers;
    }

}
