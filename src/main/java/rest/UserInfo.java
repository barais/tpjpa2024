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
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="userinfo",
        urlPatterns={"/UserInfo"})
public class UserInfo extends HttpServlet {

    private EntityManagerFactory emf;

    @Override
    public void init() throws ServletException {
        emf = Persistence.createEntityManagerFactory("dev");

    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        Utilisateur utilisateur = new Utilisateur();

        utilisateur.setNom(request.getParameter("nom"));
        // autres attributs

        EntityManager em = emf.createEntityManager();


        em.getTransaction().begin();
        em.persist(utilisateur);
        em.getTransaction().commit();


        out.println("<HTML>\n<BODY>\n" +
                "<H1>Recapitulatif des informations</H1>\n" +
                "<UL>\n" +
                " <LI>Nom: "
                + request.getParameter("nom") +
                "</UL>\n" +
                "</BODY></HTML>");
    }


}
