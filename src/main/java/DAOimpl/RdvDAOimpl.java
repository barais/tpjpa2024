package DAOimpl;

import DAO.RdvDao;
import Model.Rdv;
import java.util.ArrayList;
import java.util.List;

public class RdvDAOimpl implements RdvDao {

    List<Rdv> RdvList = new ArrayList<Rdv>();

    public RdvDAOimpl() {

    }

    @Override
    public Rdv getById(Long id) { return null; }

    @Override
    public void insert(Rdv rdv) { RdvList.add(rdv); }

    @Override
    public void delete(Rdv rdv) {
        RdvList.remove(rdv);
    }
}
