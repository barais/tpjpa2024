package Service;

import java.util.List;
import Model.Slot;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

public class CriteriaSlot {
    public static void main(String[] args) {

        EntityManagerFactory em = Persistence.createEntityManagerFactory( "dev" );
        EntityManager entitymanager = em.createEntityManager( );
        CriteriaBuilder criteriaBuilder = entitymanager.getCriteriaBuilder();
        CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery();
        Root<Slot> from = criteriaQuery.from(Slot.class);

        //select all records
        CriteriaQuery<Object> select = criteriaQuery.select(from);
        TypedQuery<Object> typedQuery = entitymanager.createQuery(select);
        List<Object> resultlist = typedQuery.getResultList();

        for(Object o:resultlist) {
            Slot s = (Slot)o;
            System.out.println("Id : " + s.getId() + " TimeStart : "
                    + s.getTimeStart() + " TimeEnd : " + s.getTimeEnd() + " Professional : " + s.getProfessional());
        }

        //Ordering the records
        CriteriaQuery<Object> selectBis = criteriaQuery.select(from);
        selectBis.orderBy(criteriaBuilder.asc(from.get("id")));
        TypedQuery<Object> typedQueryBis = entitymanager.createQuery(select);
        List<Object> resultlistBis = typedQueryBis.getResultList();

        for(Object o:resultlistBis){
            Slot s =(Slot)o;
            System.out.println("Id : " + s.getId() + " TimeStart : "
                    + s.getTimeStart() + " TimeEnd : " + s.getTimeEnd() + " Professional : " + s.getProfessional());
        }

        entitymanager.close( );
        em.close( );
    }
}


