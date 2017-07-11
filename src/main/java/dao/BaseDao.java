package dao;
import org.hibernate.SessionFactory;
import util.MongoDBManager;

/**
 * Created by zzy on 2017/5/3.
 */
public interface BaseDao {
    SessionFactory getSessionFactory();
    void setSessionFactory(SessionFactory sessionFactory);
    boolean save(Object obj);
    boolean update(Object obj);
    MongoDBManager getMongo();
    void setMongo(MongoDBManager mongo);
}
