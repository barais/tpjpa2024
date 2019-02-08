package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "surveys")
public class Survey implements Serializable {

    private long id;
    private String link;
    private Date endAt;
    private List<User> participants;
    private Meeting meeting;
    private List<DateAvailable> dateAvailables;
    private List<Dietary> availableDietaries;

    public Survey() {
    }

    public Survey(String link, Date endAt) {
        this.link = link;
        this.endAt = endAt;
    }

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "end_at", nullable = false)
    public Date getEndAt() {
        return endAt;
    }

    public void setEndAt(Date endAt) {
        this.endAt = endAt;
    }

    @OneToMany(targetEntity = DateAvailable.class, cascade = CascadeType.PERSIST, mappedBy = "survey")
    public List<DateAvailable> getDateAvailables() {
        return dateAvailables;
    }

    public void setDateAvailables(List<DateAvailable> dateAvailables) {
        this.dateAvailables = dateAvailables;
    }

    @Column(unique = true, nullable = false)
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @ManyToMany(targetEntity = User.class)
    @JoinTable(
            name = "survey_user",
            joinColumns = @JoinColumn(name = "survey_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }

    @ManyToOne
    @JoinColumn(name = "meeting_id", nullable = false)
    public Meeting getMeeting() {
        return meeting;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }

    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JoinColumn(name = "survey_id", nullable = false)
    public List<Dietary> getAvailableDietaries() {
        return availableDietaries;
    }

    public void setAvailableDietaries(List<Dietary> availableDietaries) {
        this.availableDietaries = availableDietaries;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Survey survey = (Survey) o;
        return id == survey.id &&
                link.equals(survey.link) &&
                endAt.equals(survey.endAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, link, endAt);
    }

    @Override
    public String toString() {
        return String.format(
                "Survey{id=%d, link='%s', endAt=%s, participants=%s, meeting=%s, dateAvailables=%s, availableDietaries=%s}",
                id, link, endAt, participants, meeting, dateAvailables, availableDietaries
        );
    }
}
