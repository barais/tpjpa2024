package dao;

import metier.Worker;

public class WorkerDao extends AbstractJpaDao<Long, Worker>{
    public WorkerDao() {
        super(Worker.class);
    }
}
