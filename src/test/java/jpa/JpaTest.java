package jpa;

import dao.UsersDao;
import model.Client;
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
            UsersDao usersDao = new UsersDao();
            Prof arnaud = new Prof();
            arnaud.setName("Arnaud");
            arnaud.setEmail("arnaud@gmail.com");
            Client yao = new Client();
            yao.setName("Yao");
            yao.setEmail("yao@Hotmail.fr");

            usersDao.save(arnaud);
            usersDao.save(yao);

        } catch (Exception e) {
            e.printStackTrace();
        }
        tx.commit();

        manager.close();
        factory.close();
    }

}
