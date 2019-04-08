package rideshare.demo.Entity;
import org.springframework.lang.NonNull;
import javax.persistence.*;

@Entity
public class City {
    @Id
    private Long cityId;
    private String name;
    private String county;
    private String province;
    private Long population;

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }

    @Override
    public String toString() {
        return "City{" +
                "cityId=" + cityId +
                ", name='" + name + '\'' +
                ", county='" + county + '\'' +
                ", province='" + province + '\'' +
                ", population=" + population +
                '}';
    }
}
