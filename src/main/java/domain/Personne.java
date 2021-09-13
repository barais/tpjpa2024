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
    private String prenom;

    public Personne(){
    }

    public Personne(String nom) {
        this.nom = nom;
    }

    public Personne(String nom, String prenom, String identifiant, String mail, String mdp) {
        this.nom = nom;
        this.prenom = prenom;
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Column(unique = true)
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

    @Column(unique = true)
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
