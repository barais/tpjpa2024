package domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class Utilisateur {
    private Long id;

    private String name;

    private Departement department;

    public Utilisateur() {
    }

    public Utilisateur(String name, Departement department) {
        this.name = name;
        this.department = department;
    }

    public Utilisateur (String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    @Override
    public String toString() {
        return "Utilisateur [id=" + id + ", name=" + name + "]";
    }
}
