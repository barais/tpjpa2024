package entities;

import javax.persistence.Entity;

@Entity
public class Patient extends Person {


    private Long id;
    private Long numSS;

    public Patient(String firstName, String lastName, Long numSS) {
        super(firstName, lastName);
        this.numSS = numSS;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumSS() {
        return numSS;
    }

    public void setNumSS(Long numSS) {
        this.numSS = numSS;
    }

}
