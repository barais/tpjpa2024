package domain;

import jakarta.persistence.*;

@Entity
public class KidToy {

    private Long id;
    private Kid kid;
    private Toy toy;

    public KidToy(Kid kid, Toy toy) {
        this.kid = kid;
        this.toy = toy;
    }

    public KidToy() {

    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    @ManyToOne
    public Kid getKid() {
        return kid;
    }

    @ManyToOne
    public Toy getToy() {
        return toy;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setKid(Kid kid) {
        this.kid = kid;
    }

    public void setToy(Toy toy) {
        this.toy = toy;
    }
}
