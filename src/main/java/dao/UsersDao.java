package dao;

import model.Users;

public class UsersDao extends AbstractJpaDao<String,Users> {

    public UsersDao(){
        super(Users.class);
    }
}
