package dao;

import java.util.List;
import entity.Order;




/**
 * Created by zzy on 2017/4/19.
 */
public interface OrderDao extends BaseDao{
    //@SuppressWarnings("uncheck")
    List<Order> getUserOrders(int userid);
    List<Order> getOrders();
    Integer getTotalAmount();
    boolean changeState(int orderid,char state);
    boolean destroyOrder(int orderid);

    /*
    public boolean saveOrder(Order order){
        Session session = getSessionFactory().getCurrentSession();
        try {
            //session.beginTransaction();
            session.save(order);
            //session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            //session.getTransaction().rollback();
            return false;
        }
    }
    */

}
