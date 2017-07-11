package service.impl;


import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.ActionContext;
import dao.UserDao;
import entity.User;
import service.AuthService;
/**
 * Created by zzy on 2017/5/4.
 */
public class AuthServiceImpl extends AuthService{
    private UserDao userDao;

    @Override
    public boolean isLogin(){
        Map<String, Object> session = ActionContext.getContext().getSession();
        return session.containsKey("user");
    }

    @Override
    @Transactional(readOnly = true)
    public String login(User user){
        //test wheather struts can get username and password
        //System.out.println(user.getUsername());
        //System.out.println(user.getPassword());
        if(user!=null)
            System.out.println(user);
        else
            return "input";

        if(isLogin())		//if user has logged in, redirect to index
            return "success";
        //UserDao userDao = new UserDao();
        User userDB = userDao.getUserByUsername(user.getUsername());
        //System.out.println(userDB.getUsername());
        //System.out.println(userDB.getPassword());
        //System.out.println(userDB.getRole());
        System.out.println("userDB: ");
        System.out.println(userDB);
        if(userDB!=null && userDB.getPassword().equals(user.getPassword())){
            user.setUserid(userDB.getUserid());
            user.setRole(userDB.getRole());
            allow_passport(user);
            return "success";
        }
        else
            return "input";
    }

    @Override
    public String logout(){
        Map<String, Object> session = ActionContext.getContext().getSession();
        if(session.containsKey("user")){
            session.remove("user");
            return "success";
        }
        else
            return "input";
    }

    @Override
    @Transactional(readOnly = false)
    public String register(User user){
        //System.out.println(user.getUsername());
        //System.out.println(user.getPassword());
        //UserDao userDao = new UserDao();
        //confirm the username not exists
        if(userDao.getUserByUsername(user.getUsername())==null)
        {
            //All new users' role is set as user
            user.setRole("user");
            user.setProfileid("");
            userDao.save(user);
            System.out.println(user);
            allow_passport(userDao.getUserByUsername(user.getUsername()));
            return "success";
        } else {
            System.out.println("reg.\n");
            return "reg";
        }
    }

    @Override
    protected void allow_passport(User user) {
        Map<String, Object> session = ActionContext.getContext().getSession();
        //user.setPassword(null);//erase the information of password
        session.put("user", user);
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }


}
