package Service;

import dao.RdvDao;
import dao.UsersDao;
import jpa.EntityManagerHelper;
import lombok.Data;
import model.Rdv;
import model.Users;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Data
public class UserService {
    
    private UsersDao  usersDao = new UsersDao();
    private RdvDao rdvDao = new RdvDao();
    private RdvService rdvService = new RdvService();

    public Optional<Users>getUser(final Long  id){
        return usersDao.findOne(id);
    }

    private EntityManager entityManager = EntityManagerHelper.getEntityManager();
    public Iterable<Users> getUsers()   {
            return usersDao.findAll();
    }
    public Iterable<Users> getProfs()   {
        return usersDao.findAllProfs();
    }
    public Iterable<Users> getClients()   {
        return usersDao.findAllClients();
    }
    public Iterable<Users> getUsersByName(String name)   {
        return usersDao.findByName(name);
    }
    public Iterable<Users> getUsersByLastname(String lastname)   {
        return usersDao.findByLastname(lastname);
    }


    public void  deleteUser(final Long id){
        if (usersDao.existsById(id)) {
            for (Rdv rdv: rdvDao.findByUser(id)) {
                rdvService.deleteRdv(rdv.getId());
            }
            usersDao.deleteById(id);
        }
    }

    public Users saveUser(Users user) {
        return usersDao.save(user);
    }
    public Users modifyUser(final Long id, Users user){
        if (usersDao.existsById(id)) {

            Users userTOModify = getUser(id).get();
            userTOModify.setEmail(user.getEmail());
            userTOModify.setLastname(user.getLastname());
            userTOModify.setName(user.getName());

            return usersDao.update(userTOModify);
        }
        return user;
    }
}
