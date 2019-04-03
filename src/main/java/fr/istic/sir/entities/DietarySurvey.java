package fr.istic.sir.entities;

import org.codehaus.jackson.annotate.JsonManagedReference;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@DiscriminatorValue("DietarySurvey")
public class DietarySurvey extends Survey {
    private List<Dietary> dietaries;

    @JsonManagedReference
    @OneToMany(mappedBy = "survey", cascade = CascadeType.PERSIST)
    public List<Dietary> getDietaries() {
        return dietaries;
    }

    public void setDietaries(List<Dietary> dietaries) {
        this.dietaries = dietaries;
    }
}
