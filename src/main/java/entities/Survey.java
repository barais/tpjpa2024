package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "surveys")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "discrimator_type")
public abstract class Survey implements Serializable {

    private long id;

    private String link;

    private Date endAt;

    private Meeting meeting;

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

    @Temporal(TemporalType.DATE)
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
                "Survey{id=%d, link='%s', endAt=%s, meeting=%s}",
                id, link, endAt, meeting
        );
    }
}
