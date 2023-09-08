package jpa.CRUD;

import domain.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class UserCRUD {
    private final EntityManager manager;
    private final TypedQuery<User> loginQuery;

    public UserCRUD(EntityManager manager) {
        this.manager = manager;
        loginQuery = manager.createNamedQuery("User.login", User.class);
    }

    public boolean login(String username, String password) {
        loginQuery.setParameter("username", username);
        loginQuery.setParameter("password", password);
        return loginQuery.getResultList().size() == 1;
    }

    public void register(String firstName, String lastName, String username, String password) {
        if(login(username, password)) {
            throw new IllegalArgumentException("User already exists");
        }
        manager.persist(new domain.User(firstName, lastName, username, password));
    }

    public User getUser(String username, String password) {
        loginQuery.setParameter("username", username);
        loginQuery.setParameter("password", password);
        User user = loginQuery.getSingleResult();
        if(user == null) {
            throw new IllegalArgumentException("User does not exist");
        }
        return user;
    }

    public void updatePassword(String username, String password) {
        Query updateQuery = manager.createQuery("UPDATE User u SET u.password = ?1 WHERE u.username = ?2");
        updateQuery.setParameter(1, password);
        updateQuery.setParameter(2, username);
        updateQuery.executeUpdate();
    }

    public void deleteUser(String username, String password){
        Query deleteQuery = manager.createQuery("DELETE FROM User u WHERE u.username = ?1 AND u.password = ?2");
        deleteQuery.setParameter(1, username);
        deleteQuery.setParameter(2, password);
        deleteQuery.executeUpdate();
    }
}
