package ServiceCriteriaQuery;

import java.util.List;
import Model.Student;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

public class CriteriaQStudent {
    public static void main(String[] args) {

        EntityManagerFactory em = Persistence.createEntityManagerFactory( "dev" );
        EntityManager entitymanager = em.createEntityManager( );
        CriteriaBuilder criteriaBuilder = entitymanager.getCriteriaBuilder();
        CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery();
        Root<Student> from = criteriaQuery.from(Student.class);

        //select all records
        CriteriaQuery<Object> select = criteriaQuery.select(from);
        TypedQuery<Object> typedQuery = entitymanager.createQuery(select);
        List<Object> resultlist = typedQuery.getResultList();

        for(Object o:resultlist) {
            Student s = (Student)o;
            System.out.println("Id : " + s.getId() + " Name : " + s.getName() + " RdvList : " + s.getRdvList());
        }

        //Ordering the records
        CriteriaQuery<Object> selectBis = criteriaQuery.select(from);
        selectBis.orderBy(criteriaBuilder.asc(from.get("name")));
        TypedQuery<Object> typedQueryBis = entitymanager.createQuery(select);
        List<Object> resultlistBis = typedQueryBis.getResultList();

        for(Object o:resultlistBis){
            Student s =(Student)o;
            System.out.println("Id : " + s.getId() + " Name : " + s.getName() + " RdvList : " + s.getRdvList());
        }

        entitymanager.close( );
        em.close( );
    }
}
