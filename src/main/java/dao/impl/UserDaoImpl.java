package dao.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import dao.UserDao;
import entity.User;
import org.bson.types.ObjectId;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by zzy on 2017/6/15.
 */
public class UserDaoImpl extends BaseDaoImpl implements UserDao{
    /*
    public boolean usersLogin(User u) {
        String hql = "";
        try {
            Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            hql = "from User where username=? and password=?";
            Query query = session.createQuery(hql);
            query.setParameter(0, u.getUsername());
            query.setParameter(1, u.getPassword());
            List list = query.list();
            session.getTransaction().commit();
            if( list.size() > 0 )
                return true;
            else
                return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    */
    @Override
    public User getUserByUsername(String username) {
        try {
            String hql = "from User where username=:name";
            List list = getSessionFactory().getCurrentSession().createQuery(hql).setParameter("name", username).list();
            if( list.size() > 0 )
                return (User) list.get(0);
        } catch (Exception e) {
            System.out.println("Exception: cannnot get user bu user name.\n");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getUserByid(int userid) {
        try {
            String hql = "from User where userid=:id";
            List list = getSessionFactory().getCurrentSession().createQuery(hql).setParameter("id", userid).list();
            if( list.size() > 0 )
                return (User) list.get(0);
        } catch (Exception e) {
            System.out.println("Exception: cannnot get user bu user id.\n");
            e.printStackTrace();
        }
        return null;
    }

    /*
    public User saveUser(User u) {
        //String hql = "";
        //Session session = getSessionFactory().getCurrentSession();
        try {
            //session.beginTransaction()
            getSessionFactory().getCurrentSession().save(u);
            //session.save(u);
            //session.getTransaction().commit();
            System.out.println("userDao saved user.\n");
            return u;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    */
    @Override
    public boolean destroyUser(int userid) {
        try {
            User user = getSessionFactory().getCurrentSession().load(User.class, userid);
            getSessionFactory().getCurrentSession().delete(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }




    @Override
    public Integer getTotalAmount(){
        try {
            String hql = "select count(*) from User";
            int num = ((Long)getSessionFactory().getCurrentSession().createQuery(hql).iterate().next()).intValue();
            return num;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public List<User> getUsers(){
        try {
            String hql = "from User";
            List<User> users = getSessionFactory().getCurrentSession().createQuery(hql).list();
            return users;
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    @Override
    public  boolean updateUserProfile(User user){
        try{



            //System.out.println(user.getUserid());
            User u = getUserByid(user.getUserid());
            //System.out.println(u);
            //u.setName(user.getName());
            DBCollection collection = getMongo().getDB().getCollection("userprofile");	// get collection of mongoDB
            BasicDBObject search = new BasicDBObject();								// search Object
            if(u.getProfileid() == null||u.getProfileid().equals("")) {										// if the user do not has profileID, insert it into mongo and get profileID
                collection.insert(search);
                u.setProfileid(search.get("_id").toString());
            } else {																// or get the search object
                search.put("_id", new ObjectId(u.getProfileid()));
            }

            BasicDBObject updateObject = new BasicDBObject();
            String[] field  = {"Message","Imageid"};
            for(int i = 0; i < field.length; ++i) {
                Method m = User.class.getMethod("get" + field[i]);
                Object tmp = m.invoke(user);
                if(tmp != null && !tmp.equals(""))
                    updateObject.append(field[i].toLowerCase(), tmp.toString());
            }

            BasicDBObject updateQuery = new BasicDBObject();						// create the update object
            updateQuery.append("$set", updateObject);
            collection.update(search, updateQuery);									// update profile in mongoDB
            update(u);
            return true;



        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public User getUserProfile(int userid) {
        try {
            User user = getUserByid(userid);
            if(user.getProfileid() == null||user.getProfileid().equals(""))											// the user has no profile in mongoDB
                return user;
            DBCollection collection = getMongo().getDB().getCollection("userprofile");	// get collection of mongoDB
            BasicDBObject searchObj = new BasicDBObject();								// search Object
            searchObj.append("_id", new ObjectId(user.getProfileid()));
            DBObject profile = collection.findOne(searchObj);
            String[] field  = {"Message","Imageid"};
            for(int i = 0; i < field.length; ++i) {
                if(profile.containsField(field[i].toLowerCase())) {
                    Method method = user.getClass().getMethod("set" + field[i], String.class);
                    method.invoke(user, profile.get(field[i].toLowerCase()));
                }
            }
            return user;
        } catch ( Exception e ) {
            e.printStackTrace();
            return null;
        }    }

}
