package servlet;

import dao.DoctorDAO;
import dao.SpecialisationDAO;
import entities.Doctor;
import entities.Specialisation;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet; import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest; import javax.servlet.http.HttpServletResponse;

@WebServlet(name="doctorInfo", urlPatterns={"/DoctorInfo"})
public class DoctorInfo extends HttpServlet {

    DoctorDAO doctorDAO;
    SpecialisationDAO speDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        this.doctorDAO = new DoctorDAO();
        this.speDAO = new SpecialisationDAO();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String doctorFirstName = request.getParameter("firstName");
        String doctorLastName = request.getParameter("lastName");
        String spe = request.getParameter("specialisation");

        //Print doctor information on web page
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<HTML>\n<BODY>\n" +
                "<H1>Doctor Information</H1>\n" +
                "<UL>\n" + " <LI>First Name: "
                + doctorFirstName + "\n" +
                " <LI>Last Name: "
                + doctorLastName + "\n" + " <LI>Specialisation: "
                + spe + "\n" + "</UL>\n"
                + "<a href='index.html'>Home page</a>\n" +
                "</BODY></HTML>");

        //Post doctor information in database
        postDoctorInfo(doctorFirstName, doctorLastName, spe);

    }

    public void postDoctorInfo(String doctorFirstName, String doctorLastName, String specialisation) {
        Doctor newDoctor = new Doctor();
        Specialisation spe = new Specialisation(specialisation);

        newDoctor.setFirstName(doctorFirstName);
        newDoctor.setLastName(doctorLastName);
        newDoctor.setSpecialisation(spe);

        //Save new specialisation and new doctor as a DAO
        speDAO.save(spe);
        doctorDAO.save(newDoctor);
    }

}