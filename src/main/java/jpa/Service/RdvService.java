package jpa.Service;

import java.util.Date;

import javax.persistence.EntityManager;

import jpa.Entity.Etudiant;
import jpa.Entity.Professeur;
import jpa.Entity.Rdv;

public class RdvService {

    private EntityManager manager;

    public RdvService(EntityManager entityManager){
        this.manager = entityManager;
    }

    //cré des rdv disponibles pour les etudiants
    public Rdv createPossibleRdv(Date dateDebut, long duration, Professeur professeur){
        Date dateFin = new Date();
        dateFin.setTime(dateDebut.getTime() + duration); // 1h base
        Rdv ret = new Rdv(professeur, dateDebut, dateFin);
        return ret;
    }
    public Rdv createPossibleRdv(Date dateDebut, Professeur professeur){
        Rdv ret = new Rdv(professeur, dateDebut);
        return ret;
    }
    
    //assign un étudiant au rdv
    public void prendreRdv(Rdv rdv, Etudiant etudiant){
        rdv.setEtudiant(etudiant);
        manager.persist(rdv);
        manager.getTransaction().commit();
    }
        //decaler un rdv
    public void decalerRdv(Rdv rdv, Date heureDebut){
        //Récupère la dff entre début et fin pour avoir la durée
        Date debut = rdv.getheureDebut();
        Date fin = rdv.getHeureFin();
        int diff = debut.compareTo(fin);
        rdv.setHeureDebut(heureDebut);
        //réplique la durée pour générer l'heure de fin
        Date dateFin = new Date();
        dateFin.setTime(heureDebut.getTime() + diff); 
        rdv.setHeureFin(dateFin);
        manager.persist(rdv);
        manager.getTransaction().commit();
    }
    //supprimer un rdv
    public void supprimerRdv(Rdv rdv){
        manager.remove(rdv);
        manager.getTransaction().commit();
    }

    //décommander un rdv
    public void decommandRdv(Rdv rdv){
        rdv.setEtudiant(null);
        manager.persist(rdv);
        manager.getTransaction().commit();
    }
}