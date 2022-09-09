package entities;

public class Patient extends Personne {
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

    private Long id;
    private Long numSS;
}
