package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import domain.Departement;
import domain.Professionnel;
import domain.Rdv;

public class RdvDao {
    private EntityManager manager;

    public RdvDao (javax.persistence.EntityManager manager) {
        this.manager = manager;
    }

    public void createRdvs() {
        int numOfRdvs = manager.createQuery("Select a From Rdv a", Rdv.class).getResultList().size();
        if (numOfRdvs == 0) {
            Departement departement = new Departement("java");
            Professionnel professionnel = new Professionnel("professionnel", departement);
            manager.persist(departement);
            manager.persist(professionnel);

            manager.persist(new Rdv("MAN", 2, professionnel));
            manager.persist(new Rdv("TAA", 3, professionnel));
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
