package fr.istic.sir.entities;

import org.codehaus.jackson.annotate.JsonManagedReference;

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

    private List<Meeting> meetings;

    public User() {
    }

    public User(String email, String lastName, String firstName) {
        this.email = email;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    @Id
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(nullable = false, name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(nullable = false, name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonManagedReference
    @OneToMany(mappedBy = "creator", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    public List<Meeting> getMeetings() {
        return meetings;
    }

    public void setMeetings(List<Meeting> meetings) {
        this.meetings = meetings;
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
        return String.format(
                "User{email='%s', lastName='%s', firstName='%s', meetings=%s}",
                email, lastName, firstName, meetings
        );
    }
}
