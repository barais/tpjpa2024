package dao;

import model.Client;
import model.Users;

import java.util.List;

public class UsersDao extends AbstractJpaDao<Long, Users> {

    public UsersDao(){
        super(Users.class);
    }

    public List<Users> findAllProfs() {
        return entityManager.createQuery("select e from Prof as e",Users.class).getResultList();
    }

    public List<Users> findAllClients() {
        return entityManager.createQuery("select e from Client as e", Users.class).getResultList();
    }

    public List<Users> findByName(String s) {
        return entityManager.createQuery("select e from Users as e where e.name ='" +s+ "'", Users.class).getResultList();
    }

    public List<Users> findByLastname(String s) {
        return entityManager.createQuery("select e from Users as e where e.lastname =='" +s+ "'", Users.class).getResultList();
    }
}
