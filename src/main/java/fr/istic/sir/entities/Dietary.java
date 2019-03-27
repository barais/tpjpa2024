package fr.istic.sir.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "dietaries")
public class Dietary implements Serializable {
    private long id;

    private String name;

    private Survey survey;

    private List<User> voters;

    public Dietary() {
    }

    public Dietary(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(targetEntity = Survey.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "survey_id", nullable = false)
    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    @ManyToMany
    @JoinTable(
            name = "dietary_user",
            joinColumns = @JoinColumn(name = "dietary_id", referencedColumnName = "id"),
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
        Dietary dietary = (Dietary) o;
        return id == dietary.id &&
                name.equals(dietary.name) &&
                Objects.equals(voters, dietary.voters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return String.format("Dietary{id=%d, name='%s', voters=%s}", id, name, voters);
    }
}
