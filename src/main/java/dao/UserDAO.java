package dao;
import metier.*;

public class UserDAO extends AbstractJpaDao<Long, User>{

    public UserDAO() {
        super(User.class);
    }

}
