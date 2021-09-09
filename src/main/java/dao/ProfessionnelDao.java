package dao;

import domain.Departement;
import domain.Professionnel;
import jpa.EntityManagerHelper;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class ProfessionnelDao {

    private EntityManager manager;

    public ProfessionnelDao (EntityManager manager) {
        this.manager = manager;
    }

    public static void main(String[] args) {
        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("example");
        EntityManager manager = factory.createEntityManager();
        ProfessionnelDao test = new ProfessionnelDao (manager);

        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        try {
            test.createProfessionnels();
        } catch (Exception e) {
            e.printStackTrace();
        }
        tx.commit();

        test.listProfessionnels();

        manager.close();
        System.out.println(".. done");
    }


    private void createProfessionnels() {
        int numOfEmployees = manager.createQuery("Select a From Professionnel a", Professionnel.class).getResultList().size();
        if (numOfEmployees == 0) {
            Departement departement = new Departement("java");
            manager.persist(departement);

            manager.persist(new Professionnel("Jakab Gipsz",departement));
            manager.persist(new Professionnel("Captain Nemo",departement));

        }
    }

    private void listProfessionnels() {
        List<Professionnel> resultList = manager.createQuery("Select a From Professionnel a", Professionnel.class).getResultList();
        System.out.println("num of employess:" + resultList.size());
        for (Professionnel next : resultList) {
            System.out.println("next employee: " + next);
        }
    }


}
