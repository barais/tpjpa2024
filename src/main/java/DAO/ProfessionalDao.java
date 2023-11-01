package DAO;

import Model.Professional;
import Model.Slot;
import java.util.List;

public interface ProfessionalDao extends DAO<Professional> {


    List<Slot> setSlotsAvailable(List<Slot> newSlotsAvailable);

    void setTimeSlot(Slot timeSlot);

    void setTitledSlot(String titledSlot);
}
