package DAO;

import Model.Professional;
import Model.Slot;
import java.util.List;

public interface ProfessionalDao extends DAO<Professional> {

    List<Slot> getAllSlots();

    void setNewSlots(List<Slot> newSlots);

    void setTimeStart(Long timeStart);

    void setTimeSlot(Long timeSlot);
}
