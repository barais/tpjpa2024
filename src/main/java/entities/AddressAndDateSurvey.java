package entities;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("AddressAndDateSurvey")
public class AddressAndDateSurvey extends Survey {

    private List<entities.Date> dates;

    private List<Address> addresses;

    public AddressAndDateSurvey(String link, java.util.Date endAt) {
        super(link, endAt);
    }

    public AddressAndDateSurvey() {
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<entities.Date> getDates() {
        return dates;
    }

    public void setDates(List<Date> dates) {
        this.dates = dates;
    }
}
