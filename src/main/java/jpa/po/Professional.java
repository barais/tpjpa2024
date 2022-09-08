package jpa.po;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Professional {
    private Long id;
    private String firstName;
    private String lastName;

    private List<Appointment> listAppointments = new ArrayList<>();

    private List<Patient> listPatients = new ArrayList<>();

    public Professional() {}

    public Professional(String firstName, String lastName) {
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

    @OneToMany(mappedBy = "professional", cascade = CascadeType.PERSIST)
    public List<Appointment> getListAppointments() {
        return listAppointments;
    }

    public void setListAppointments(List<Appointment> listAppointments) {
        this.listAppointments = listAppointments;
    }

    @OneToMany(mappedBy = "professional", cascade = CascadeType.PERSIST)
    public List<Patient> getListPatients() {
        return listPatients;
    }

    public void setListPatients(List<Patient> listPatients) {
        this.listPatients = listPatients;
    }
}
