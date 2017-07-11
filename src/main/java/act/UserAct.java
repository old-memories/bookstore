package act;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ActionContext;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import service.UserService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zzy on 2017/5/11.
 */
public class UserAct extends ActionSupport {
    private int userid;
    private int rows;
    private int page;
    private User user;
    private Map<String,Object> dataMap = new HashMap<String,Object>();
    @Autowired
    private UserService userService;
    public Map<String, Object> getDataMap() {
        return dataMap;
    }
    public void setDataMap(Map<String, Object> dataMap) {
        this.dataMap = dataMap;
    }
    public int getRows(){return rows;}
    public void setRows(int rows){this.rows=rows;}
    public int getPage(){return page;}
    public void setPage(int page){this.page=page;}

    public User getUser(){return user;}
    public void setUser(User user){this.user=user;}
    public int getUserid(){return userid;}
    public void setUserid(int userid){this.userid=userid;}
    public String getUsers(){
        dataMap.clear();
        dataMap.put("total",userService.getTotalAmount());
        dataMap.put("rows",userService.getUsers());
        dataMap.put("success",true);
        return "ajax";
    }
    public String saveUser(){
        System.out.println("saving user...");
        dataMap.clear();
        System.out.println(user);
        dataMap.put("success",userService.saveUser(user));
        System.out.println("saved user...");
        return"ajax";
    }

    public String updateUser(){
        System.out.println("updating user...");
        dataMap.clear();
        System.out.println("userid: "+userid);
        user.setUserid(userid);
        System.out.println(user);
        dataMap.put("success",userService.updateUser(user));
        System.out.println("updated user...");
        return "ajax";
    }

    public String destroyUser(){
        System.out.println("destroying user...");
        dataMap.clear();
        System.out.println(userid);
        dataMap.put("success",userService.destroyUser(userid));
        System.out.println("destroyed user...");
        return "ajax";
    }


    public String getUserProfile(){
        User user=(User)ActionContext.getContext().getSession().get("user");
        ActionContext.getContext().put("user",userService.getUserProfile(user.getUserid()));
        return "show_profile";
    }

    public String updateUserProfile(){
        User u=(User)ActionContext.getContext().getSession().get("user");
        user.setUserid(u.getUserid());
        if(userService.updateUserProfile(user,""))
            return "update_profile";
        else
            return "fail";
    }

}
