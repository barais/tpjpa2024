package dao;

import domaine.Projects;

public class ProjectsDao extends AbstractJpaDao<Long, Projects> {
    public ProjectsDao(){
        this.setClazz(Projects.class);
    }
}

