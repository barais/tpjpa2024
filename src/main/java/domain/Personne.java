package domain;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Personne {

    private Long id;
    private String nom;
    private String identifiant;
    private String mdp;
    private String mail;

    public Personne(){
    }

    public Personne(String nom) {
        this.nom = nom;
    }

    public Personne(String nom, String identifiant, String mail, String mdp) {
        this.nom = nom;
        this.mail = mail;
        this.identifiant = identifiant;
        this.mdp = mdp;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
