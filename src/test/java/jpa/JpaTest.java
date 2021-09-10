package jpa;

import model.Prof;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence
                .createEntityManagerFactory("dev");
        EntityManager manager = factory.createEntityManager();

        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        try {
            Prof arnaud = new Prof();
            arnaud.setName("Arnaud");
            Prof yao = new Prof();
            yao.setName("Yao");

            manager.persist(arnaud);
            manager.persist(yao);

        } catch (Exception e) {
            e.printStackTrace();
        }
        tx.commit();

        manager.close();
        factory.close();
    }

}
