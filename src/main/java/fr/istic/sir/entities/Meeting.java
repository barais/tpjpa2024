package fr.istic.sir.entities;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "meetings")
public class Meeting implements Serializable {

    private long id;

    private String title;

    private String summary;

    private Date startAt;

    private Date endAt;

    private List<Survey> surveys;

    private User creator;

    private List<User> participants;

    public Meeting(String title, String summary, Date startAt, Date endAt) {
        this.title = title;
        this.summary = summary;
        this.startAt = startAt;
        this.endAt = endAt;
    }

    public Meeting() {
    }

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(nullable = false)
    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_at")
    public Date getStartAt() {
        return startAt;
    }

    public void setStartAt(Date startAt) {
        this.startAt = startAt;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_at")
    public Date getEndAt() {
        return endAt;
    }

    public void setEndAt(Date endAt) {
        this.endAt = endAt;
    }

    @JsonManagedReference
    @OneToMany(mappedBy = "meeting", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    public List<Survey> getSurveys() {
        return surveys;
    }

    public void setSurveys(List<Survey> surveys) {
        this.surveys = surveys;
    }

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "creator_id", referencedColumnName = "email")
    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "meeting_user",
            joinColumns = @JoinColumn(name = "meeting_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "email")
    )
    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meeting meeting = (Meeting) o;
        return id == meeting.id &&
                title.equals(meeting.title) &&
                summary.equals(meeting.summary) &&
                Objects.equals(startAt, meeting.startAt) &&
                Objects.equals(endAt, meeting.endAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, summary, startAt, endAt);
    }
}
