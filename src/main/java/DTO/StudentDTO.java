package DTO;

import Model.Slot;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.io.Serializable;
import java.util.List;

public class StudentDTO extends UserDTO implements Serializable {

    private List<RdvDTO> RdvList;

    public StudentDTO() {
    }

    public StudentDTO(String name, String password, List<RdvDTO> RdvList) {
        super(name, password);
        this.RdvList = RdvList;
    }

    public List<RdvDTO> getRdvList() {
        return RdvList;
    }

    public void setRdvList(List<RdvDTO> RdvList) {
        this.RdvList = RdvList;
    }

    public void addRdv(RdvDTO rdv) { this.RdvList.add(rdv); }

    public void removeRdv(RdvDTO rdv) { this.RdvList.remove(rdv); }

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
    EntityManager em = factory.createEntityManager();
    CriteriaBuilder cb = em.getCriteriaBuilder();

    CriteriaQuery<RdvDTO> cq = cb.createQuery(RdvDTO.class);
    Root<RdvDTO> from = cq.from(RdvDTO.class);

    //cq.select(RdvDTO);
    TypedQuery<RdvDTO> q = em.createQuery(cq);
    List<RdvDTO> allitems = q.getResultList();
}
