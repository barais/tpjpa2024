package servlets;
import dao.UserDAO;
import jpa.EntityManagerHelper;
import metier.Appointment;
import metier.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="afficheUserInfo",
        urlPatterns={"/AfficheUserInfo"})
public class AfficheUserInfo extends HttpServlet{


    static EntityManager manager;
    public void doGet(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
        manager = EntityManagerHelper.getEntityManager();

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        List<User> userList = manager.createNamedQuery("allUser",User.class).getResultList();

        System.out.println(userList.size());

        out.append("<HTML>\n<BODY>\n" + "<H1> Recapitulatif des informations de Appointment </H1>\n"
                    + "<UL>\n" + " <LI> User Total :  " + userList.size());

        for(User u : userList){
            out.append("<LI> ID: " + u.getId()+"\n"
            +"<LI> Nom: " + u.getName() +"\n"
            +"<LI> BirthDate: " + u.getDateNaissance()+"\n");
        }

        out.append("</UL>\n" +
                "</BODY></HTML>");

        out.println();

    }
}
