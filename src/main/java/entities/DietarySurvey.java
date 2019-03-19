package entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@DiscriminatorValue("DietarySurvey")
public class DietarySurvey extends Survey {
    private List<Dietary> dietaries;

    public DietarySurvey(String link, Date endAt) {
        super(link, endAt);
    }

    public DietarySurvey() {
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<Dietary> getDietaries() {
        return dietaries;
    }

    public void setDietaries(List<Dietary> dietaries) {
        this.dietaries = dietaries;
    }
}
