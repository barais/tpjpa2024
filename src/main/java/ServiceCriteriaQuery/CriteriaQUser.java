package ServiceCriteriaQuery;

import java.util.List;
import Model.User;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

public class CriteriaQUser {
    public static void main(String[] args) {

        EntityManagerFactory em = Persistence.createEntityManagerFactory( "dev" );
        EntityManager entitymanager = em.createEntityManager( );
        CriteriaBuilder criteriaBuilder = entitymanager.getCriteriaBuilder();
        CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery();
        Root<User> from = criteriaQuery.from(User.class);

        //select all records
        CriteriaQuery<Object> select = criteriaQuery.select(from);
        TypedQuery<Object> typedQuery = entitymanager.createQuery(select);
        List<Object> resultlist = typedQuery.getResultList();

        for(Object o:resultlist) {
            User u = (User)o;
            System.out.println("Id : " + u.getId() + " Name : " + u.getName() + " Password : " + u.getPassword());
        }

        //Ordering the records
        CriteriaQuery<Object> selectBis = criteriaQuery.select(from);
        selectBis.orderBy(criteriaBuilder.asc(from.get("name")));
        TypedQuery<Object> typedQueryBis = entitymanager.createQuery(select);
        List<Object> resultlistBis = typedQueryBis.getResultList();

        for(Object o:resultlistBis){
            User u =(User)o;
            System.out.println("Id : " + u.getId() + " Name : " + u.getName() + " Password : " + u.getPassword());
        }

        entitymanager.close( );
        em.close( );
    }
}


