package jpa.po;

import javax.persistence.*;
import java.util.List;

@Entity
public class Patient {
    private Long id;
    private String lastName;
    private String firstName;
    private List<Appointment> appointments;

    @ManyToOne
    private Professional professional;

    public Patient() {
        super();
    }

    public Patient(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String lastName) {
        this.firstName = lastName;
    }

    @OneToMany(mappedBy = "patient", cascade = CascadeType.PERSIST)
    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public Professional getProfessionals() {
        return professional;
    }

    public void setProfessionals(Professional professional) {
        this.professional = professional;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                '}';
    }
}
