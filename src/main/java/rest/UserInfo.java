package rest;

import domain.Utilisateur;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="adduserinfo",
        urlPatterns={"/UserInfo"})
public class UserInfo extends HttpServlet {

    private EntityManagerFactory factory;

    @Override
    public void init() throws ServletException {
        factory = Persistence.createEntityManagerFactory("dev");
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Création de l'objet utilisateur
        Utilisateur user = new Utilisateur();
        user.setNom(request.getParameter("nom"));

        // Ajout données à la database
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(user);
        manager.getTransaction().commit();
        manager.close();

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<HTML>\n<BODY>\n" +
                "<H1>Recapitulatif des informations de l'utilisateur</H1>\n" +
                "<UL>\n" +
                " <LI>Nom : "
                + request.getParameter("nom") +
                "</UL>\n" +
                "</BODY></HTML>");
    }

    @Override
    public void destroy() {
        factory.close();
    }
}
