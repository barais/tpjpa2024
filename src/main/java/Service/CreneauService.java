package Service;

import dao.CreneauDao;
import dao.RdvDao;
import lombok.Data;
import model.Creneau;
import model.Rdv;

import java.util.Optional;

@Data
public class CreneauService {
    private CreneauDao creneauDao = new CreneauDao();
    private RdvDao rdvDao = new RdvDao();
    private RdvService rdvService = new RdvService();

    public Optional<Creneau> getCreneau(final Long id){
        return creneauDao.findOne(id);
    }

    public Iterable<Creneau> getCreneaux()   {
        return creneauDao.findAll();
    }
    
    public void  deleteCreneau(final Long id){
        if (creneauDao.existsById(id)) {

            for (Rdv rdv : rdvDao.findByCrenel(id)){
                rdvService.deleteRdv(rdv.getId());
            }
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
