package DAOimpl;

import DAO.ProfessionalDao;
import Model.Professional;
import Model.Slot;
import java.util.List;

public class ProfessionalDAOimpl implements ProfessionalDao {

    Professional pro;
    List<Slot> SlotList;
    Slot slot;

    @Override
    public void insert(Professional professional) {

    }

    @Override
    public void update(Professional professional) {

    }

    @Override
    public void delete(Professional professional) {

    }

    @Override
    public List<Slot> setSlotsAvailable(List<Slot> newSlotsAvailable) {
        this.SlotList = newSlotsAvailable;
        return newSlotsAvailable;
    }

    @Override
    public void setTimeSlot(Slot timeSlot) {
        pro.addSlot(timeSlot);
    }

    @Override
    public void setTitledSlot(String titledSlot) {
        slot.setTitledSlot(titledSlot);
    }
}
