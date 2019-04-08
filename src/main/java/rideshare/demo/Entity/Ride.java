package rideshare.demo.Entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Ride {
    private Long rideId;
    private Long ownerId;
    private String description;
    private Place from;
    private Place to;
    private Integer numberOfSeats;
    private Integer bookedSeats;
    private Double price;
    private Date date;
    private List<Long> passengers;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getRideId() {
        return rideId;
    }

    public void setRideId(Long rideId) {
        this.rideId = rideId;
    }


    @ManyToOne(cascade = CascadeType.ALL)
    public Place getFrom() {
        return from;
    }

    public void setFrom(Place from) {
        this.from = from;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    public Place getTo() {
        return to;
    }

    public void setTo(Place to) {
        this.to = to;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @ElementCollection
    public List<Long> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Long> passengers) {
        this.passengers = passengers;
    }

}
