package servlets;

import dao.UserDAO;
import jpa.EntityManagerHelper;
import metier.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@WebServlet(name="updateUser",
        urlPatterns={"/UpdateUser"})
public class UpdateUser extends HttpServlet {

    public void doPost(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {


        String name = request.getParameter("name");
        request.getParameter("birthday");
        try {
            Date bd= new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("birthday"));
            User userToUpdate = new User(name,bd);
            UserDAO userDAO = new UserDAO();
            userDAO.update(userToUpdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        response.sendRedirect("/index.html");


    }

}
