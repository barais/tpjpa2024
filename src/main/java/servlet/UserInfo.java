package servlet;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="userinfo", urlPatterns={"/UserInfo"})

public class UserInfo extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String firstname = request.getParameter("firstname");
        String age = request.getParameter("age");

        out.println("<html><body>");
        out.println("<h1>Récapitulatif des informations</h1>");
        out.println("<ul>");
        out.println("<li>Nom: " + name + "</li>");
        out.println("<li>Prénom: " + firstname + "</li>");
        out.println("<li>Age: " + age + "</li>");
        out.println("</ul>");

        // Redirection vers la servlet accueilUser
        response.sendRedirect(request.getContextPath() + "/accueilUser?name=" + name + "&firstname=" + firstname);
    }
}


