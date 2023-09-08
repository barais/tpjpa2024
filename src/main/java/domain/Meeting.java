package domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Meeting {

    private Long id;
    private String label;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime timeSlotStart;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime timeSlotEnd;
    private User host;

    public Meeting() {
    }

    public Meeting(String label,
                   LocalDateTime timeSlotStart,
                   LocalDateTime timeSlotEnd,
                   User host) {
        this.label = label;
        this.timeSlotStart = timeSlotStart;
        this.timeSlotEnd = timeSlotEnd;
        this.host = host;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String name) {
        this.label = name;
    }

    public LocalDateTime getTimeSlotStart() {
        return timeSlotStart;
    }

    public void setTimeSlotStart(LocalDateTime timeSlotStart) {
        this.timeSlotStart = timeSlotStart;
    }

    public LocalDateTime getTimeSlotEnd() {
        return timeSlotEnd;
    }

    public void setTimeSlotEnd(LocalDateTime timeSlotEnd) {
        this.timeSlotEnd = timeSlotEnd;
    }

    @ManyToOne
    public User getHost() {
        return host;
    }

    public void setHost(User host) {
        this.host = host;
    }
}
