package Service;

import dao.CreneauDao;
import lombok.Data;
import model.Creneau;

import java.util.Optional;

@Data
public class CreneauService {
    private CreneauDao creneauDao = new CreneauDao();

    public Optional<Creneau> getCreneau(final Long id){
        return creneauDao.findOne(id);
    }

    public Iterable<Creneau> getCreneaux()   {
        return creneauDao.findAll();
    }
    
    public void  deleteCreneau(final Long id){
        if (creneauDao.existsById(id)) {
            creneauDao.deleteById(id);
        }
    }

    public Creneau saveCreneau(Creneau creneau){
        return creneauDao.save(creneau);
    }
    public Creneau modifyCreneau(final Long id, Creneau creneau){
        if (creneauDao.existsById(id)) {
            return creneauDao.save(creneau);
        }
        return creneau;
    }    
}
