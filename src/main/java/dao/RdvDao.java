package dao;

import domain.Departement;
import domain.Professionnel;
import domain.Rdv;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class RdvDao {
    private EntityManager manager;

    public RdvDao (javax.persistence.EntityManager manager) {
        this.manager = manager;
    }

    public static void main(String[] args) {
        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("example");
        EntityManager manager = factory.createEntityManager();
        RdvDao test = new RdvDao (manager);

        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        try {
            test.createRdvs();
        } catch (Exception e) {
            e.printStackTrace();
        }
        tx.commit();

        test.listRdvs();

        manager.close();
        System.out.println(".. done");
    }


    private void createRdvs() {
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

    private void listRdvs() {
        List<Rdv> resultList = manager.createQuery("Select a From Rdv a", Rdv.class).getResultList();
        System.out.println("num of rdvs:" + resultList.size());
        for (Rdv next : resultList) {
            System.out.println("next rdv: " + next);
        }
    }
}
