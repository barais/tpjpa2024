package servlet;

import dao.PatientDAO;
import entities.Patient;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet; import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest; import javax.servlet.http.HttpServletResponse;

@WebServlet(name="patientInfo", urlPatterns={"/PatientInfo"})
public class PatientInfo extends HttpServlet {

    PatientDAO patientDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        this.patientDAO = new PatientDAO();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String patientFirsName = request.getParameter("firstName");
        String patientLastName = request.getParameter("lastName");
        String patientNumSS = request.getParameter("numSS");

        //Print patient information on web page
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<HTML>\n<BODY>\n" +
                "<H1>Patient Information</H1>\n" +
                "<UL>\n" + " <LI>First Name: "
                + patientFirsName + "\n" +
                " <LI>Last Name: "
                + patientLastName + "\n" + " <LI>Social Security Number: "
                + patientNumSS + "\n" + "</UL>\n"
                + "<a href='index.html'>Home page</a>\n" +
                "</BODY></HTML>");

        //Post patient information in database
        postPatientInfo(patientFirsName, patientLastName, patientNumSS);

    }

    public void postPatientInfo(String patientFirsName, String patientLastName, String patientNumSS) {
        Patient newPatient = new Patient();
        newPatient.setFirstName(patientFirsName);
        newPatient.setLastName(patientLastName);
        newPatient.setNumSS(Long.parseLong(patientNumSS));

        patientDAO.save(newPatient);
    }

}