package DAOimpl;

import DAO.RdvDao;
import Model.Rdv;

public class RdvDAOimpl implements RdvDao {
    @Override
    public void insert(Rdv rdv) {
        RdvList.add(rdv);
    }

    @Override
    public void delete(Rdv rdv) {
        RdvList.remove(rdv);
    }
}
