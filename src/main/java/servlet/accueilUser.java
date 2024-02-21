package servlet;

import java.io.IOException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@WebServlet(name="accueilUser", urlPatterns={"/accueilUser"})
public class accueilUser extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String firstname = request.getParameter("firstname");

        // Affichage du message de bienvenue personnalisé
        out.println("<html><body>");
        out.println("<h1>Bienvenue, " + firstname + " " + name + "!</h1>");
        out.println("</body></html>");
    }
}
