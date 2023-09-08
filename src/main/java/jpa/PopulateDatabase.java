package jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jpa.CRUD.RoomCRUD;
import jpa.CRUD.UserCRUD;

public class PopulateDatabase {

    public static void main(String[] args) {
        EntityManager manager = EntityManagerHelper.getEntityManager();
        UserCRUD userCRUD = new UserCRUD(manager);
        RoomCRUD roomCRUD = new RoomCRUD(manager);
        EntityTransaction tx = manager.getTransaction();

        tx.begin();

        try {
            // Clear la base de données
            manager.createQuery("DELETE FROM Meeting ").executeUpdate();
            manager.createQuery("DELETE FROM MeetingRoom ").executeUpdate();
            manager.createQuery("DELETE FROM Office ").executeUpdate();
            manager.createQuery("DELETE FROM User").executeUpdate();

            // Ajoute des utilisateurs
            userCRUD.register("John", "Doe", "jdoe", "password");
            userCRUD.register("Jane", "Doe", "jane", "password");
            userCRUD.register("Jack", "Doe", "jack", "password");

            // Ajoute des salle de réunion
            roomCRUD.createMeetingRoom(10, 1, "Salle 1");
            roomCRUD.createMeetingRoom(10, 2, "Salle 2");
            roomCRUD.createMeetingRoom(7, 3, "Salle 3");
            roomCRUD.createMeetingRoom(3, 4, "Salle 4");
            roomCRUD.createMeetingRoom(5, 5, "Salle 5");

            // Ajoute des bureaux
            roomCRUD.createOffice(1, 6);
            roomCRUD.createOffice(1, 7);
            roomCRUD.createOffice(1, 8);
            roomCRUD.createOffice(1, 9);
            roomCRUD.createOffice(1, 10);

            // Assignation des bureaux
            roomCRUD.assignOffice(6, userCRUD.getUser("jdoe", "password"));
            roomCRUD.assignOffice(7, userCRUD.getUser("jane", "password"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        tx.commit();

        manager.close();
        EntityManagerHelper.closeEntityManagerFactory();
        System.out.println(".. done");
    }
}
