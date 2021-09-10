package jpa.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Etudiant implements Personne{
    private Long id;

    private String name;

    private String email;

    private List<Rdv> rdvs = new ArrayList<Rdv>();

    public Etudiant(){
        this.name = "dummy";
    }

    public Etudiant(String name){
        this.name = name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return this.email;
    }

    @Id
    @GeneratedValue
    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id=id;
    }

    @OneToMany(mappedBy = "etudiant")
    public List<Rdv> getRdvs(){
        return this.rdvs;
    }

    public void setRdvs(List<Rdv> rdvs){
        this.rdvs = rdvs;
    }

    public String toString(){
        return "L'etudiant " + name + " à pris " + rdvs.size() + "rdvs";
    }
}
