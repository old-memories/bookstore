package service;

import entity.Order;

import java.util.List;

/**
 * Created by zzy on 2017/5/4.
 */
public interface OrderService {
    List<Order> getUserOrders();
    List<Order> getOrders();
    Order createOrder();
    Integer getTotalAmount();
    boolean confirmOrder(int orderid);
    boolean cancelOrder(int orderid);
    boolean destroyOrder(int orderid);

}
