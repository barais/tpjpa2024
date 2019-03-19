package entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@DiscriminatorValue("AddressSurvey")
public class AddressSurvey extends Survey {

    private List<Address> addresses;

    public AddressSurvey(String link, Date endAt) {
        super(link, endAt);
    }

    public AddressSurvey() {
    }

    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}
