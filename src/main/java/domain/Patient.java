package domain;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class Patient extends Person implements Serializable {

    private Long numSS;

    public Patient() {}

    public Patient(String firstName, String lastName, Long numSS) {
        super(firstName, lastName);
        this.numSS = numSS;
    }

    public Long getNumSS() {
        return numSS;
    }

    public void setNumSS(Long numSS) {
        this.numSS = numSS;
    }

}
