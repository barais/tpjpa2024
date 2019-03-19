package entities;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("DateSurvey")
public class DateSurvey extends Survey {

    private List<Date> dates;

    public DateSurvey(String link, java.util.Date endAt) {
        super(link, endAt);
    }

    public DateSurvey() {
    }

    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<Date> getDates() {
        return dates;
    }

    public void setDates(List<Date> dates) {
        this.dates = dates;
    }
}
