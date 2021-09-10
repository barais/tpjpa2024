package jpa;

import dao.UsersDao;
import model.Client;
import model.Prof;

public class JpaTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        UsersDao usersDao = new UsersDao();

        try {
            Prof arnaud = new Prof();
            arnaud.setName("Arnaud");
            arnaud.setEmail("arnaud@gmail.com");
            Client yao = new Client();
            yao.setName("Yao");
            yao.setEmail("yao@Hotmail.fr");

            usersDao.save(arnaud);
            usersDao.save(yao);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
