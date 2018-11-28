package rideshare.demo.Entity;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

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



    public User() {
    }

    public User(String password, String email, String firstName, String lastName) {

        this.email = email;
        this.password = password;
        this.firstName=firstName;
        this.lastName=lastName;

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

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
