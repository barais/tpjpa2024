package dao;

import domain.Departement;
import domain.Professionnel;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class DepartementDao {

    private EntityManager manager;

    public DepartementDao (javax.persistence.EntityManager manager) {
        this.manager = manager;
    }

    public static void main(String[] args) {
        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("example");
        EntityManager manager = factory.createEntityManager();
        DepartementDao test = new DepartementDao (manager);

        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        try {
            test.createDepartements();
        } catch (Exception e) {
            e.printStackTrace();
        }
        tx.commit();

        test.listDepartements();

        manager.close();
        System.out.println(".. done");
    }


    private void createDepartements() {
        int numOfDepartements = manager.createQuery("Select a From Departement a", Departement.class).getResultList().size();
        if (numOfDepartements == 0) {
            manager.persist(new Departement("MAN"));
            manager.persist(new Departement("TAA"));
        }
    }

    private void listDepartements() {
        List<Departement> resultList = manager.createQuery("Select a From Departement a", Departement.class).getResultList();
        System.out.println("num of departements:" + resultList.size());
        for (Departement next : resultList) {
            System.out.println("next departement: " + next);
        }
    }
}
