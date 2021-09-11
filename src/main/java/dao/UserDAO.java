package dao;
import metier.*;
import org.hibernate.jdbc.WorkExecutor;

import java.util.List;

public class UserDAO extends AbstractJpaDao<Long, User>{

    public UserDAO() {
        super(User.class);
    }

    public List <User>  getAllUser(){
        String query = "select e from User as e";
        return this.entityManager.createQuery(query).getResultList();
    }

    public Worker getBestReatedWorker(){
        String query = "select e from User as e where";
        return (Worker) this.entityManager.createQuery(query).getSingleResult();
    }


}
