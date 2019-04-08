package rideshare.demo.Entity;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    @Column(unique = true,nullable = false)
    @Email(message = "Niepoprawny format email")
    private String email;
    @NotNull(message = "Pole Hasło nie może być puste")
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String address;
    private String phone;
    private Double rate;
    private Long rateAmount=0L;
    private String gender;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] photo;
    private String description;
    private String car;

    public User() {
    }

    public User(String password, String email, String firstName, String lastName, String address, String phone, LocalDate birthDate, String description, String car) {

        this.email = email;
        this.password = password;
        this.firstName=firstName;
        this.lastName=lastName;
        this.phone = phone;
        this.gender = gender;
        this.birthDate = birthDate;
        this.address = address;
        this.description = description;
        this.car = car;
    }

    public Long getuserId() {
        return userId;
    }

    public void setuserId(Long userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }

    public String getGender() { return gender; }

    public void setGender(String gender) { this.gender = gender; }

    public byte[] getPhoto() { return photo; }

    public void setPhoto(byte[] photo) { this.photo = photo; }

    public LocalDate getBirthDate() { return birthDate; }

    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getCar() { return car; }

    public void setCar(String car) { this.car = car; }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Long getRateAmount() {
        return rateAmount;
    }

    public void setRateAmount(Long rateAmount) {
        this.rateAmount = rateAmount;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
