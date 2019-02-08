package entities;

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

    public Meeting() {
    }

    public Meeting(String title, String summary, Date startAt, Date endAt) {
        this.title = title;
        this.summary = summary;
        this.startAt = startAt;
        this.endAt = endAt;
    }

    @Column(name = "start_at")
    @Temporal(TemporalType.DATE)
    public Date getStartAt() {
        return startAt;
    }

    public void setStartAt(Date startAt) {
        this.startAt = startAt;
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

    @Column(name = "end_at")
    @Temporal(TemporalType.DATE)
    public Date getEndAt() {
        return endAt;
    }

    public void setEndAt(Date endAt) {
        this.endAt = endAt;
    }

    @OneToMany(targetEntity = Survey.class, mappedBy = "meeting")
    public List<Survey> getSurveys() {
        return surveys;
    }

    public void setSurveys(List<Survey> surveys) {
        this.surveys = surveys;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meeting meeting = (Meeting) o;
        return id == meeting.id &&
                title.equals(meeting.title) &&
                Objects.equals(summary, meeting.summary) &&
                Objects.equals(startAt, meeting.startAt) &&
                Objects.equals(endAt, meeting.endAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, summary, startAt, endAt);
    }

    @Override
    public String toString() {
        return String.format("Meeting{id=%d, title='%s', summary='%s', startAt=%s, endAt=%s}", id, title, summary, startAt, endAt);
    }
}
