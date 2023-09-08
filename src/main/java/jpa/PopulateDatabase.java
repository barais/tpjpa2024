package jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jpa.CRUD.UserCRUD;

public class PopulateDatabase {

    public static void main(String[] args) {
        EntityManager manager = EntityManagerHelper.getEntityManager();
        UserCRUD userCRUD = new UserCRUD(manager);
        EntityTransaction tx = manager.getTransaction();

        tx.begin();

        try {
            // Ajoute des utilisateurs
            userCRUD.register("John", "Doe", "jdoe", "password");
            userCRUD.register("Jane", "Doe", "jane", "password");
            userCRUD.register("Jack", "Doe", "jack", "password");

        } catch (Exception e) {
            e.printStackTrace();
        }

        tx.commit();

        manager.close();
        EntityManagerHelper.closeEntityManagerFactory();
        System.out.println(".. done");
    }
}
