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

    private Meeting meeting;

    private List<SurveyDate> availableDates;

    private List<Dietary> availableDietaries;

    public Survey(String link, Date endAt) {
        this.link = link;
        this.endAt = endAt;
    }

    public Survey() {
    }

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(nullable = false, unique = true)
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Column(name = "end_at")
    public Date getEndAt() {
        return endAt;
    }

    public void setEndAt(Date endAt) {
        this.endAt = endAt;
    }

    @ManyToOne
    @JoinColumn(name = "meeting_id", nullable = false)
    public Meeting getMeeting() {
        return meeting;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }

    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL)
    public List<SurveyDate> getAvailableDates() {
        return availableDates;
    }

    public void setAvailableDates(List<SurveyDate> availableDates) {
        this.availableDates = availableDates;
    }

    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL)
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
                "Survey{id=%d, link='%s', endAt=%s, meeting=%s, availableDates=%s, availableDietaries=%s}",
                id, link, endAt, meeting, availableDates, availableDietaries
        );
    }
}
