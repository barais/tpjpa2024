package DAOimpl;

import DAO.ProfessionalDao;
import Model.Professional;
import Model.Slot;
import java.util.List;

public class ProfessionalDAOimpl implements ProfessionalDao {

    Professional pro;

    public ProfessionalDAOimpl() {

    }

    @Override
    public Professional getById(Long professionalId) { return null; }

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
    public void setNewSlots(List<Slot> newSlots) { pro.setSlotList(newSlots); }

    @Override
    public void setTimeStart(Slot slot, Long timeStart) { slot.setTimeStart(timeStart); }

    @Override
    public void setTimeEnd (Slot slot, Long timeEnd) { slot.setTimeEnd(timeEnd); }
}
