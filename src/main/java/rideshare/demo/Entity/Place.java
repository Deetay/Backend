package rideshare.demo.Entity;
import javax.persistence.*;


@Entity
public class Place {
    private Long placeId;
    private City city;
    private String placeInfo;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }

    @ManyToOne(cascade = CascadeType.MERGE)
    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getPlaceInfo() {
        return placeInfo;
    }

    public void setPlaceInfo(String placeInfo) {
        this.placeInfo = placeInfo;
    }

    @Override
    public String toString() {
        return
                city.getName() +
                ", '" + placeInfo + '\'' +
                ' ';
    }
}
