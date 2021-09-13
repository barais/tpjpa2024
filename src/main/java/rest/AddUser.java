package rest;

import dao.UtilisateurDao;
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

@WebServlet(name="adduser",
        urlPatterns={"/AddUser"})
public class AddUser extends HttpServlet {

    private EntityManagerFactory factory;

    @Override
    public void init() throws ServletException {
        factory = Persistence.createEntityManagerFactory("dev");
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager manager = factory.createEntityManager();
        UtilisateurDao userDao = new UtilisateurDao(manager);

        // Création de l'objet utilisateur
        Utilisateur user = new Utilisateur();
        user.setNom(request.getParameter("nom"));
        user.setPrenom(request.getParameter("prenom"));

        // Ajout données à la database
        manager.getTransaction().begin();
        userDao.addUser(user);
        manager.getTransaction().commit();
        manager.close();

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<HTML>\n<BODY>\n" +
                "<H1>Recapitulatif des informations de l'utilisateur</H1>\n" +
                "<UL>\n" +
                " <LI>Nom : "
                + request.getParameter("nom") +
                ", Prénom : "
                + request.getParameter("prenom") +
                "</UL>\n" +
                "</BODY></HTML>");
    }

    @Override
    public void destroy() {
        factory.close();
    }
}
