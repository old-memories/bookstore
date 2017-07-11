package dao.impl;

import dao.BaseDao;
import org.hibernate.SessionFactory;
import util.MongoDBManager;

/**
 * Created by zzy on 2017/6/15.
 */
public class BaseDaoImpl implements BaseDao{
    private SessionFactory sessionFactory;
    private MongoDBManager mongo;
    @Override
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    @Override
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public boolean save(Object obj) {
        try {
            getSessionFactory().getCurrentSession().save(obj);
            System.out.println("end BaseDao.save.\n");
            return true;
        } catch (Exception e) {
            System.out.println("exception: save.\n");
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public boolean update(Object obj) {
        try {
            getSessionFactory().getCurrentSession().update(obj);
            System.out.println("end BaseDao.update.\n");
            return true;
        } catch (Exception e) {
            System.out.println("exception: update.\n");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public MongoDBManager getMongo() {
        return mongo;
    }
    @Override
    public void setMongo(MongoDBManager mongo) {
        this.mongo = mongo;
    }
}
