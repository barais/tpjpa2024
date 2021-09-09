package jpa;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Rdv {
    private Long id;

    private Date heureDebut;

    private Date heureFin;

    private Professeur professeur;

    private Etudiant etudiant;

    public Rdv(Professeur professeur, Date heureDebut){
        this.heureDebut = heureDebut;
        this.professeur = professeur;
    }
    public Rdv(Professeur professeur, Date heureDebut, Etudiant etudiant){
        this.heureDebut = heureDebut;
        this.heureFin = new Date();
        heureFin.setTime(heureDebut.getTime() + 3600000); // 1h base
        this.professeur = professeur;
        this.etudiant = etudiant;
    }

    public void setHeureDebut(Date heureDebut){
        this.heureDebut = heureDebut;
    }

    public Date getheureDebut(){
        return this.heureDebut;
    }

    public void setHeureFin(Date heureFin){
        this.heureFin = heureFin;
    }

    public Date getHeureFin(){
        return this.heureFin;
    }

    @Id
    @GeneratedValue
    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id=id;
    }

    @ManyToOne
    public Professeur getProfesseur(){
        return this.professeur;
    }
    
    public void setProfesseur(Professeur professeur){
        this.professeur = professeur;
    }

        
    @ManyToOne
    public Etudiant getEtudiant(){
        return this.etudiant;
    }
    
    public void setEtudiant(Etudiant etudiant){
        this.etudiant = etudiant;
    }

    @Override
    public String toString(){
        return "Le rendez-vous " + this.id + ", de " + this.heureDebut + " a " + this.heureFin + " avec " + this.professeur.getName() + " est pris par " + this.etudiant.getName();
    }   
}
