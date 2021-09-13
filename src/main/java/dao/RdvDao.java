package dao;

import java.util.List;

import javax.persistence.EntityManager;

import domain.Departement;
import domain.Professionnel;
import domain.Rdv;
import domain.Utilisateur;

public class RdvDao {
    private EntityManager manager;

    public RdvDao (javax.persistence.EntityManager manager) {
        this.manager = manager;
    }

    public void createRdvs() {
        int numOfRdvs = manager.createQuery("Select a From Rdv a", Rdv.class).getResultList().size();
        if (numOfRdvs == 0) {
            Departement departement = new Departement("Java2");
            Professionnel professionnel = new Professionnel("Prof 3", departement);
            Utilisateur utilisateur = new Utilisateur("User 3");
            manager.persist(departement);
            manager.persist(professionnel);
            manager.persist(utilisateur);

            manager.persist(new Rdv("MAN", 2, professionnel, utilisateur));
            manager.persist(new Rdv("TAA", 3, professionnel, utilisateur));
        }
    }

    public void listRdvs() {
        List<Rdv> resultList = manager.createQuery("Select a From Rdv a", Rdv.class).getResultList();
        System.out.println("Nombre de rdvs :" + resultList.size());
        for (Rdv next : resultList) {
            System.out.println("Rdv suivant : " + next);
        }
    }
}
