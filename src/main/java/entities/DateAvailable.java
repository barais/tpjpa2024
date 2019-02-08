package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "date_availables")
public class DateAvailable implements Serializable {
    private long id;

    private Date date;

    private boolean pause = false;

    private Survey survey;

    private List<User> voters;

    public DateAvailable() {
    }

    public DateAvailable(Date date, boolean pause) {
        this.date = date;
        this.pause = pause;
    }

    public DateAvailable(Date date) {
        this(date, false);
    }

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @ManyToMany(targetEntity = User.class)
    @JoinTable(
            name = "date_vote",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "date_available_id")
    )
    public List<User> getVoters() {
        return voters;
    }

    public void setVoters(List<User> voters) {
        this.voters = voters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DateAvailable that = (DateAvailable) o;
        return id == that.id &&
                date.equals(that.date) &&
                survey.equals(that.survey) &&
                voters.equals(that.voters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, survey, voters);
    }

    @Override
    public String toString() {
        return "DateAvailable{" +
                "id=" + id +
                ", date=" + date +
                ", survey=" + survey.getLink() +
                ", voters=" + voters +
                '}';
    }

    @ManyToOne
    @JoinColumn(name = "survey_id", nullable = false)
    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    public boolean isPause() {
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }
}
