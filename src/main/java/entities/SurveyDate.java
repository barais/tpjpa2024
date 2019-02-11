package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "survey_dates")
public class SurveyDate implements Serializable {
    private long id;

    private Date date;

    private boolean pause = false;

    private Survey survey;

    private List<User> voters;

    public SurveyDate(Date date, boolean pause) {
        this.date = date;
        this.pause = pause;
    }

    public SurveyDate() {
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

    @Column(nullable = false)
    public boolean isPause() {
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    @ManyToOne
    @JoinColumn(name = "survey_id", nullable = false)
    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    @ManyToMany
    @JoinTable(
            name = "survey_date_user",
            joinColumns = @JoinColumn(name = "survey_date_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "email")
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
        SurveyDate that = (SurveyDate) o;
        return id == that.id &&
                pause == that.pause &&
                date.equals(that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, pause);
    }

    @Override
    public String toString() {
        return String.format("SurveyDate{id=%d, date=%s, pause=%s, survey=%s, voters=%s}", id, date, pause, survey, voters);
    }
}
