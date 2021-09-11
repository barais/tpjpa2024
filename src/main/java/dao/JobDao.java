package dao;

import metier.Job;

public class JobDao extends  AbstractJpaDao<Long, Job>{
    public JobDao() {
        super(Job.class);
    }
}
