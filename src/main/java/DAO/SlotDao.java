package DAO;

import Model.Slot;
import java.util.ArrayList;
import java.util.List;

public interface SlotDao extends DAO<Slot> {

    List<Slot> SlotList = new ArrayList<Slot>();
}
