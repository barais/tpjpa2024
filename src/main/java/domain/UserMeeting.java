package domain;

import jakarta.persistence.*;

@Entity
public class UserMeeting {
    public Long id;

    @Column(nullable = false)
    public User guest;
    @Column(nullable = false)
    public Meeting meeting;

    public UserMeeting() {
    }

    public UserMeeting(User guest, Meeting meeting) {
        this.guest = guest;
        this.meeting = meeting;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    public User getGuest() {
        return guest;
    }

    public void setGuest(User guest) {
        this.guest = guest;
    }

    @OneToOne
    public Meeting getMeeting() {
        return meeting;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }

    @Override
    public String toString() {
        return "User " + guest.getUsername() +
                " is invited to meeting " + meeting.getLabel() +
                " hosted by " + meeting.getHost().getUsername() +
                " from " + meeting.getTimeSlotStart() +
                " to " + meeting.getTimeSlotEnd() + ".";

    }
}
