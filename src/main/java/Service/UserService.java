package Service;

import dao.UsersDao;
import lombok.Data;
import model.Users;

import java.util.Optional;

@Data
public class UserService {
    
    private UsersDao  usersDao = new UsersDao();

    public Optional<Users>getUser(final String id){
        return usersDao.findOne(id);
    }

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

    public void  deleteUser(final String id){
        if (usersDao.existsById(id)) {
            usersDao.deleteById(id);
        }
    }

    public Users saveUser(Users user){
        return usersDao.save(user);
    }
    public Users modifyUser(final String id, Users user){
        if (usersDao.existsById(id)) {
            return usersDao.save(user);
        }
        return user;
    }
}
