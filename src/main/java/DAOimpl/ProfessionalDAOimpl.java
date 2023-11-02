package DAOimpl;

import DAO.ProfessionalDao;
import DAO.StudentDao;
import Model.Professional;
import Model.Slot;
import java.util.List;

public class ProfessionalDAOimpl implements ProfessionalDao {

    Professional pro;
    List<Slot> SlotList;
    Slot slot;

    public ProfessionalDAOimpl() {

    }

    @Override
    public Professional getById(Long professionalId) {
        ProfessionalDao professionalDao = new ProfessionalDAOimpl();
        return pro = professionalDao.getById(professionalId);
    }

    @Override
    public void insert(Professional professional) {

    }

    @Override
    public void delete(Professional professional) {

    }

    @Override
    public List<Slot> getAllSlots() { return pro.getSlotList(); }

    @Override
    public Professional createProfessional(Professional pro) {
        return pro;
    }

    @Override
    public void setNewSlots(List<Slot> newSlots) { this.SlotList = newSlots; }

    @Override
    public void setTimeStart(Long timeStart) { this.slot.setTimeStart(timeStart); }

    @Override
    public void setTimeSlot (Long timeSlot) { this.slot.setTimeEnd(timeSlot); }
}
