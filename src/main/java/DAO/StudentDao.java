package DAO;

import Model.Professional;
import Model.Rdv;
import Model.Slot;
import Model.Student;
import java.util.List;

public interface StudentDao extends DAO<Student> {

    List<Rdv> getAllRdv();

    Student createStudent(Student stu);

    List<Slot> consultSlots(Professional pro);

    boolean bookedSlot(Rdv rdv);
}
