package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User implements Serializable {
    private String email;

    private String lastName;

    private String firstName;

    private List<Survey> surveys;

    private List<DateAvailable> dateChoices;

    private List<Dietary> dietaryChoices;

    public User() {
    }

    public User(String email, String lastName, String firstName) {
        this.email = email;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    @Id
    @Column(nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "first_name", nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @ManyToMany(targetEntity = Survey.class, mappedBy = "participants")
    public List<Survey> getSurveys() {
        return surveys;
    }

    public void setSurveys(List<Survey> surveys) {
        this.surveys = surveys;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return email.equals(user.email) &&
                lastName.equals(user.lastName) &&
                firstName.equals(user.firstName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, lastName, firstName);
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                '}';
    }

    @ManyToMany(targetEntity = DateAvailable.class, mappedBy = "voters")
    public List<DateAvailable> getDateChoices() {
        return dateChoices;
    }

    public void setDateChoices(List<DateAvailable> dateChoices) {
        this.dateChoices = dateChoices;
    }

    @ManyToMany(targetEntity = Dietary.class)
    @JoinTable(
            name = "dietary_vote",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "dietary_id")
    )
    public List<Dietary> getDietaryChoices() {
        return dietaryChoices;
    }

    public void setDietaryChoices(List<Dietary> dietaryChoices) {
        this.dietaryChoices = dietaryChoices;
    }
}
