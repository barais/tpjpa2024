package fr.istic.sir.entities;

import org.codehaus.jackson.annotate.JsonManagedReference;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@DiscriminatorValue("AddressSurvey")
public class AddressSurvey extends Survey {

    private List<Address> addresses;

    @JsonManagedReference
    @OneToMany(mappedBy = "survey", cascade = CascadeType.PERSIST)
    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}
