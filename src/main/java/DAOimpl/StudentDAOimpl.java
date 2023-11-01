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
    public List<Slot> consultSlots(Professional pro) {
        return pro.getSlotList();
    }

    @Override
    public void bookedSlot (Rdv rdv) {
        stu.addRdv(rdv);
    }
}
