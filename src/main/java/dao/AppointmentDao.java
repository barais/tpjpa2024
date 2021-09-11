package dao;

import metier.*;

public class AppointmentDao extends AbstractJpaDao<Long, Appointment> {

    public AppointmentDao() {
        super(Appointment.class);
    }

}
