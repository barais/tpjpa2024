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

public class ProfessionalDTO extends UserDTO implements Serializable {

    private List<SlotDTO> SlotList;

    public ProfessionalDTO() {

    }

    public ProfessionalDTO(String name, String password, List<SlotDTO> SlotList) {
        super(name, password);
        this.SlotList = SlotList;
    }

    public List<SlotDTO> getSlotList() {
        return SlotList;
    }

    public void setSlotList(List<SlotDTO> SlotList) {
        this.SlotList = SlotList;
    }

    public void addSlot(SlotDTO slot) { this.SlotList.add(slot); }

    public void removeSlot(SlotDTO slot) { this.SlotList.remove(slot); }

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
    EntityManager em = factory.createEntityManager();
    CriteriaBuilder cb = em.getCriteriaBuilder();

    CriteriaQuery<SlotDTO> cq = cb.createQuery(SlotDTO.class);
    Root<SlotDTO> from = cq.from(SlotDTO.class);

    //cq.select(SlotDTO);
    TypedQuery<SlotDTO> q = em.createQuery(cq);
    List<SlotDTO> allitems = q.getResultList();
}
