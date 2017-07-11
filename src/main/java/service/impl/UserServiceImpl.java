package service.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import dao.UserDao;
import entity.User;
import org.bson.types.ObjectId;
import org.springframework.transaction.annotation.Transactional;
import service.UserService;
import util.MD5Generator;
import util.MongoDBManager;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

/**
 * Created by zzy on 2017/5/18.
 */
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService{
    private UserDao userDao;
    //private MongoDBManager mongo;
    @Override
    @Transactional(readOnly = true)
    public List<User> getUsers(){
        List<User> users = userDao.getUsers();
        return users;
    }
    @Override
    @Transactional(readOnly=false)
    public boolean saveUser(User user){
        return userDao.save(user);
    }

    @Override
    @Transactional(readOnly=false)
    public boolean updateUser(User user){
        return userDao.update(user);
    }

    @Override
    @Transactional(readOnly=false)
    public boolean destroyUser(int userid){
        return userDao.destroyUser(userid);
    }

    @Override
    @Transactional(readOnly=true)
    public Integer getTotalAmount(){
        return userDao.getTotalAmount();
    }

    @Override
    @Transactional(readOnly = false)
    public User getUserProfile(int userid){
        /*
        try {
            User user = userDao.getUserByid(userid);
            if(user.getProfileid() == null||user.getProfileid().equals(""))											// the user has no profile in mongoDB
                return user;
            DBCollection collection = mongo.getDB().getCollection("userprofile");	// get collection of mongoDB
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
        }
        */
        return userDao.getUserProfile(userid);
    }

    @Override
    @Transactional(readOnly = false)
    public boolean updateUserProfile(User user,String filenameMD5){
            //System.out.println(user.getUserid());
            //User u = userDao.getUserByid(user.getUserid());
            //System.out.println(u);
            //u.setName(user.getName());


            if(!filenameMD5.equals("")){
                user.setImageid(filenameMD5);      //update name
            }
            /*
            DBCollection collection = mongo.getDB().getCollection("userprofile");	// get collection of mongoDB
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
            */
            //userDao.update(u);													// update user in mysql
            return userDao.updateUserProfile(user);
    }

    public void setUserDao(UserDao userDao){
        this.userDao=userDao;
    }
    public UserDao getUserDao(){
        return userDao;
    }


}
