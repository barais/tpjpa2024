package jpa.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Appointment {

    private Long id;

    private String reason;
    private LocalDateTime startingTime;

    private Professional professional;
    private Patient patient;

    public Appointment() {}

    public Appointment(String reason, LocalDateTime startingTime, Patient patient, Professional professional) {
        this.reason = reason;
        this.startingTime = startingTime;
        this.patient = patient;
        this.professional = professional;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDateTime getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(LocalDateTime startingTime) {
        this.startingTime = startingTime;
    }

    @ManyToOne
    public Professional getProfessional() {
        return professional;
    }

    public void setProfessional(Professional professional) {
        this.professional = professional;
    }

    @ManyToOne
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", reason='" + reason + '\'' +
                ", startingTime=" + startingTime +
                ", professional=" + professional.toString() +
                ", patient=" + patient.toString() +
                '}';
    }
}
