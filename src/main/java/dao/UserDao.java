package dao;

import java.util.List;





import entity.User;

/**
 * Created by zzy on 2017/4/12.
 */
public interface UserDao extends BaseDao{
    User getUserByUsername(String username);
    User getUserByid(int userid);
    boolean destroyUser(int userid);
    Integer getTotalAmount();
    List<User> getUsers();
    boolean updateUserProfile(User user);
    User getUserProfile(int userid);


}
