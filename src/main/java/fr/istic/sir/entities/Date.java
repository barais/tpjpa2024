package fr.istic.sir.entities;

import org.codehaus.jackson.annotate.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "dates")
public class Date implements Serializable {
    private long id;

    private java.util.Date time;

    private Survey survey;

    private List<User> voters;

    public Date() {
    }

    public Date(java.util.Date time) {
        this.time = time;
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
    @Temporal(TemporalType.TIMESTAMP)
    public java.util.Date getTime() {
        return time;
    }

    public void setTime(java.util.Date time) {
        this.time = time;
    }
    @ManyToMany
    @JoinTable(
            name = "date_user",
            joinColumns = @JoinColumn(name = "date_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "email")
    )
    public List<User> getVoters() {
        return voters;
    }

    public void setVoters(List<User> voters) {
        this.voters = voters;
    }

    @JsonBackReference
    @ManyToOne(targetEntity = Survey.class, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "survey_id", nullable = false)
    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }
}
