package servlet;
import dao.UtilisateurDao;
import domaine.Utilisateur;
import utils.Role;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="userinfo",
        urlPatterns={"/UserInfo"})
public class UserInfo extends HttpServlet {

    UtilisateurDao udao;

    @Override
    public void init() throws ServletException {
        super.init();
        this.udao = new UtilisateurDao();
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setName(request.getParameter("name"));
        utilisateur.setUsername(request.getParameter("username"));
        utilisateur.setRole(Role.valueOf(request.getParameter("role")));
        udao.save(utilisateur);

        out.println("<HTML>\n<BODY>\n" +
                "<H1>Recapitulatif des informations</H1>\n" +
                "<UL>\n" +
                " <LI>Nom: "
                + request.getParameter("name") + "\n" +
                " <LI>Username: "
                + request.getParameter("username") + "\n" +
                " <LI>Role: "
                + Role.valueOf(request.getParameter("role")) + "\n" +
                "</UL>\n" +
                "</BODY></HTML>");
    }
}
