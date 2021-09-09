package dao;

import domain.Departement;
import domain.Professionnel;


import javax.persistence.EntityManager;
import java.util.List;

public class ProfessionnelDao {

    private EntityManager manager;

    public ProfessionnelDao (EntityManager manager) {
        this.manager = manager;
    }


    public void createProfessionnels() {
        int numOfEmployees = manager.createQuery("Select a From Professionnel a", Professionnel.class).getResultList().size();
        if (numOfEmployees == 0) {
            Departement departement = new Departement("java");
            manager.persist(departement);
            //Code
            manager.persist(new Professionnel("Jakab Gipsz",departement));
            manager.persist(new Professionnel("Captain Nemo",departement));

        }
    }

    public void listProfessionnels() {
        List<Professionnel> resultList = manager.createQuery("Select a From Professionnel a", Professionnel.class).getResultList();
        System.out.println("num of employess:" + resultList.size());
        for (Professionnel next : resultList) {
            System.out.println("next employee: " + next);
        }
    }


}
