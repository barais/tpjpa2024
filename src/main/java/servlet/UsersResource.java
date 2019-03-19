package servlet;

import entities.User;
import jpa.EntityManagerHelper;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/users")
public class UsersResource extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManager em = EntityManagerHelper.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            User user = new User(req.getParameter("email"), req.getParameter("last_name"), req.getParameter("first_name"));
            em.persist(user);
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
        tx.commit();

        em.close();
        EntityManagerHelper.closeEntityManagerFactory();

        resp.sendRedirect("/user.html");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
