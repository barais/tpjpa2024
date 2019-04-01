package fr.istic.sir.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@DiscriminatorValue("AddressSurvey")
public class AddressSurvey extends Survey {

    private List<Address> addresses;

    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL)
    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}
