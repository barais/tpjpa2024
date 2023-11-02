package DAOimpl;

import DAO.StudentDao;
import Model.Professional;
import Model.Rdv;
import Model.Slot;
import Model.Student;
import java.util.List;

public class StudentDAOimpl implements StudentDao {

    Student stu;

    public StudentDAOimpl() {

    }

    @Override
    public void insert(Student student) {

    }

    @Override
    public void delete(Student student) {

    }

    @Override
    public List<Slot> consultSlots(Professional pro) { return pro.getSlotList(); }

    @Override
    public boolean bookedSlot (Rdv rdv) {
        List<Slot> ls = rdv.getProfessional().getSlotList();
        for (Slot slot: ls) {
            Professional pro = slot.getProfessional();
            if (!slot.contain(rdv)) {
                continue;
            }
            if (slot.timeStart < rdv.timeStart) {
                pro.addSlot(new Slot(slot.timeStart, rdv.timeStart, pro));
            }
            if (slot.timeEnd < rdv.timeEnd) {
                pro.addSlot(new Slot(slot.timeEnd, rdv.timeEnd, pro));
            }
            pro.removeSlot(slot);
            return true;
        }
        return false;
    }
}
