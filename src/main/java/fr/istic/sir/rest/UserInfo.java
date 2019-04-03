package fr.istic.sir.rest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static java.text.MessageFormat.format;

@WebServlet(name = "UserInfo", urlPatterns = "/user-info")
public class UserInfo extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        PrintWriter out = resp.getWriter();
        out.println(
                format(
                        "<html>\n<body>\n<h1>Recapitulatif des informations</h1>\n<ul>\n <li>Nom: {0}\n <li>Prenom: {1}\n <li>Age: {2}\n</ul>\n</body></html>",
                        req.getParameter("name"), req.getParameter("firstname"), req.getParameter("age")
                )
        );

    }
}
