package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet; import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest; import javax.servlet.http.HttpServletResponse;

@WebServlet(name="patientInfo", urlPatterns={"/PatientInfo"})
public class PatientInfo extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<HTML>\n<BODY>\n" +
                "<H1>Patient Informations</H1>\n" +
                "<UL>\n" + " <LI>First Name: "
                + request.getParameter("firstName") + "\n" +
                " <LI>Last Name: "
                + request.getParameter("lastName") + "\n" + " <LI>Social Security Number: "
                + request.getParameter("numSS") + "\n" + "</UL>\n" +
                "</BODY></HTML>");
    }
}