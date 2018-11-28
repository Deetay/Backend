package rideshare.demo.Entity;
import javax.persistence.*;
import java.util.Date;

@Entity
public class Place {
    private Long localizationId;
    private City city;
    private String placeInfo;
    private Date date;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getLocalizationId() {
        return localizationId;
    }

    public void setLocalizationId(Long localizationId) {
        this.localizationId = localizationId;
    }

    @ManyToOne(cascade = CascadeType.MERGE)
    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getPlaceOfMeeting() {
        return placeInfo;
    }

    public void setPlaceOfMeeting(String placeOfMeeting) {
        this.placeInfo = placeOfMeeting;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
