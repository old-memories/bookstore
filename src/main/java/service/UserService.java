package service;

import entity.User;

import java.util.List;
/**
 * Created by zzy on 2017/5/18.
 */
public interface UserService {
    Integer getTotalAmount();
    List<User> getUsers();
    boolean saveUser(User user);
    boolean updateUser(User user);
    boolean destroyUser(int userid);
    boolean updateUserProfile(User user,String filenameMD5);
    User getUserProfile(int userid);
}
