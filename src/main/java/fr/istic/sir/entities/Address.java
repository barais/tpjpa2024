package fr.istic.sir.entities;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
public class Address {
    private long id;

    private String location;

    private Survey survey;

    public Address() {
    }

    public Address(String location) {
        this.location = location;
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
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @ManyToOne
    @JoinColumn(name = "survey_id", nullable = false)
    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }
}
