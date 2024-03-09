package dao;

import domaine.Tags;

public class TagsDao extends AbstractJpaDao<Long, Tags> {
    public TagsDao(){
        this.setClazz(Tags.class);
    }
}

