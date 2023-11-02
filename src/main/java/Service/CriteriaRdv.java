package Service;

import java.util.List;
import Model.Rdv;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

public class CriteriaRdv {
    public static void main(String[] args) {

        EntityManagerFactory em = Persistence.createEntityManagerFactory( "dev" );
        EntityManager entitymanager = em.createEntityManager( );
        CriteriaBuilder criteriaBuilder = entitymanager.getCriteriaBuilder();
        CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery();
        Root<Rdv> from = criteriaQuery.from(Rdv.class);

        //select all records
        CriteriaQuery<Object> select = criteriaQuery.select(from);
        TypedQuery<Object> typedQuery = entitymanager.createQuery(select);
        List<Object> resultlist = typedQuery.getResultList();

        for(Object o:resultlist) {
            Rdv r = (Rdv)o;
            System.out.println("Id : " + r.getId() + " Titled : "
                    + r.getTitled() + " Student : " + r.getStudent());
        }

        //Ordering the records
        CriteriaQuery<Object> selectBis = criteriaQuery.select(from);
        selectBis.orderBy(criteriaBuilder.asc(from.get("id")));
        TypedQuery<Object> typedQueryBis = entitymanager.createQuery(select);
        List<Object> resultlistBis = typedQueryBis.getResultList();

        for(Object o:resultlistBis) {
            Rdv r =(Rdv)o;
            System.out.println("Id : " + r.getId() + " Titled : "
                    + r.getTitled() + " Student : " + r.getStudent());
        }

        entitymanager.close( );
        em.close( );
    }
}