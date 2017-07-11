package dao.impl;

import dao.OrderDao;
import entity.Order;

import java.util.List;

/**
 * Created by zzy on 2017/6/15.
 */
public class OrderDaoImpl extends BaseDaoImpl implements OrderDao {
    //@SuppressWarnings("uncheck")
    @Override
    public List<Order> getUserOrders(int userid) {
        try {
            String hql = "from Order od where userid = :id ";
            List<Order> orders = getSessionFactory().getCurrentSession().createQuery(hql).setParameter("id", userid).list();
            return orders;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Order> getOrders(){
        try {
            String hql = "from Order";
            List<Order> orders = getSessionFactory().getCurrentSession().createQuery(hql).list();
            return orders;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer getTotalAmount(){
        try {
            String hql = "select count(*) from Order";
            int num = ((Long)getSessionFactory().getCurrentSession().createQuery(hql).iterate().next()).intValue();
            return num;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean changeState(int orderid,char state){
        try{
            Order order= getSessionFactory().getCurrentSession().load(Order.class,orderid);
            order.setPucharsed(state);
            getSessionFactory().getCurrentSession().update(order);
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean destroyOrder(int orderid) {
        try {
            Order order = getSessionFactory().getCurrentSession().load(Order.class, orderid);
            if(order.getPucharsed()=='C'){
                getSessionFactory().getCurrentSession().delete(order);
                return true;
            }
            else
                return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

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
