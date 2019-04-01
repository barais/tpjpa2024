package fr.istic.sir.entities;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@DiscriminatorValue("DietarySurvey")
public class DietarySurvey extends Survey {
    private List<Dietary> dietaries;

    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL)
    public List<Dietary> getDietaries() {
        return dietaries;
    }

    public void setDietaries(List<Dietary> dietaries) {
        this.dietaries = dietaries;
    }
}
