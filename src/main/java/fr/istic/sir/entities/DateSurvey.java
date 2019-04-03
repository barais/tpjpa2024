package fr.istic.sir.entities;

import org.codehaus.jackson.annotate.JsonManagedReference;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@DiscriminatorValue("DateSurvey")
public class DateSurvey extends Survey {

    private List<Date> dates;

    @JsonManagedReference
    @OneToMany(mappedBy = "survey", cascade = CascadeType.PERSIST)
    public List<Date> getDates() {
        return dates;
    }

    public void setDates(List<Date> dates) {
        this.dates = dates;
    }
}
