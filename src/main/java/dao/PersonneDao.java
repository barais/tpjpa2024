package dao;

import domain.Utilisateur;
import javax.persistence.EntityManager;
import java.util.List;
import domain.Personne;

public class PersonneDao {

    private EntityManager manager;

    public PersonneDao (EntityManager manager){
        this.manager = manager;
    }


    public void listPersonnes() {
        List<Personne> resultList = manager.createQuery("Select p From Personne p", Personne.class).getResultList();
        System.out.println("Nombre de personnes " + resultList.size());
        for (Personne next : resultList) {
            System.out.println("Personne suivante : " + next);
        }
    }
}
