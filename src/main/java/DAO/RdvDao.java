package DAO;

import Model.Rdv;
import java.util.ArrayList;
import java.util.List;

public interface RdvDao extends DAO<Rdv> {
    
    List<Rdv> RdvList = new ArrayList<Rdv>();
}
