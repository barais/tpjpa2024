package fr.istic.sir.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("AddressAndDateSurvey")
public class AddressAndDateSurvey extends Survey {

    private List<fr.istic.sir.entities.Date> dates;

    private List<Address> addresses;

    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL)
    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL)
    public List<fr.istic.sir.entities.Date> getDates() {
        return dates;
    }

    public void setDates(List<Date> dates) {
        this.dates = dates;
    }
}
