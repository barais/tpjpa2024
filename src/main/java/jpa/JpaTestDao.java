package jpa;

import java.sql.Date;

import javax.persistence.*;

import dao.AppointmentDao;
import dao.JobDao;
import dao.UserDAO;
import dao.WorkerDao;
import metier.*;

public class JpaTestDao {

    public static void main(String[] args) {
        User u1=new User("Albert", new Date(1265899000L));
        UserDAO userDAO = new UserDAO();
        userDAO.save(u1);

        Job job1=new Job("plombier",2000);
        JobDao jobDao = new JobDao();
        jobDao.save(job1);

        Worker w1=new Worker("Albert", new Date(0),job1,15);
        WorkerDao workerDao= new WorkerDao();
        workerDao.save(w1);

        Appointment a= new Appointment("J'ai mal o crane jpp", new Date(888888888L), u1, w1);
        AppointmentDao dao = new AppointmentDao();
        dao.save(a);
    }
}
