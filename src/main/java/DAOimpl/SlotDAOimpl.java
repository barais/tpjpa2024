package DAOimpl;

import DAO.SlotDao;
import Model.Slot;

public class SlotDAOimpl implements SlotDao {

    @Override
    public void insert(Slot slot) {
        SlotList.add(slot);
    }

    @Override
    public void delete(Slot slot) {
        SlotList.remove(slot);
    }
}
