package act;

import java.util.HashMap;
import java.util.Objects;
import com.opensymphony.xwork2.ActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import service.AuthService;
import com.opensymphony.xwork2.ActionSupport;

import entity.User;

public class Auth extends ActionSupport
{
    private User user;
    private HashMap<String,Object> dataMap=new HashMap<String,Object>();
    /*
    private int userid;
    private String username;
    private String password;
    private String role;
    */

    @Autowired
    private AuthService authService;

    @Override
    public String execute() throws Exception {
        return "success";
    }


    public boolean isLogin() {
        return authService.isLogin();
    }

    public String login()
    {
        return authService.login(user);
    }

    public String logout() {
        return authService.logout();
    }

    public String register()
    {
       return authService.register(user);
    }

    public void setUser(User user){
        this.user=user;
    }
    public User getUser(){
        return user;
    }
    public HashMap<String, Object> getDataMap() {
        return dataMap;
    }

    public void setDataMap(HashMap<String, Object> dataMap) {
        this.dataMap = dataMap;
    }
    /*
    public int getUserid() {return userid;}
    public void setUserid(int userid) {this.userid = userid;}
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRole() {return role;}
    public void setRole(String role) { this.role=role;}
    */
/*
    public String t(){
        System.out.println(test.get( ));
        return "success";
    }
    */
    /*
    private void allow_passport() {
    Map<String, Object> session = ActionContext.getContext().getSession();
    user.setPassword(null);//erase the information of password
    session.put("user", user);
    User user2 = (User) ActionContext.getContext().getSession().get("user");
    System.out.println(user);
    System.out.println(user2);
    }
    */
}