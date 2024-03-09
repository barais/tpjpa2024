package dao;

import domaine.Comments;

public class CommentsDao extends AbstractJpaDao<Long, Comments> {
    public CommentsDao(){
        this.setClazz(Comments.class);
    }
}

