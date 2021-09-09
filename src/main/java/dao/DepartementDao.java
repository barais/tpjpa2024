package dao;

import java.util.List;

import javax.persistence.EntityManager;

import domain.Departement;

public class DepartementDao {

    private EntityManager manager;

    public DepartementDao (javax.persistence.EntityManager manager) {
        this.manager = manager;
    }

    public void createDepartements() {
        int numOfDepartements = manager.createQuery("Select a From Departement a", Departement.class).getResultList().size();
        if (numOfDepartements == 0) {
            manager.persist(new Departement("MAN"));
            manager.persist(new Departement("TAA"));
        }
    }

    public void listDepartements() {
        List<Departement> resultList = manager.createQuery("Select a From Departement a", Departement.class).getResultList();
        System.out.println("Nombre de départements : " + resultList.size());
        for (Departement next : resultList) {
            System.out.println("Département suivant : " + next);
        }
    }
}
