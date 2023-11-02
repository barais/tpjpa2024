package DAOimpl;

import DAO.SlotDao;
import Model.Slot;
import java.util.ArrayList;
import java.util.List;

public class SlotDAOimpl implements SlotDao {

    List<Slot> SlotList = new ArrayList<Slot>();

    public SlotDAOimpl() {

    }

    @Override
    public Slot getById(Slot slot) {
        return null;
    }

    @Override
    public void insert(Slot slot) {
        SlotList.add(slot);
    }

    @Override
    public void delete(Slot slot) {
        SlotList.remove(slot);
    }
}
