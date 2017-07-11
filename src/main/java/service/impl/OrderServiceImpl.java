package service.impl;

import com.opensymphony.xwork2.ActionContext;
import dao.BookDao;
import dao.OrderDao;
import entity.*;
import org.springframework.transaction.annotation.Transactional;
import service.OrderService;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by zzy on 2017/5/4.
 */
@Transactional(readOnly=true)
public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao;
    private BookDao bookDao;
    @Override
    @Transactional(readOnly = false)
    public Order createOrder(){
        Map session= ActionContext.getContext().getSession();
        Order order=new Order();
        order.setUser((User)session.get("user"));
        System.out.println("get current user.\n");
        order.setPucharsed('N');
        Cart cart=(Cart)session.get("cart");
        System.out.println("get current cart.\n");
        if(cart.getGoods().isEmpty()){
            return null ;
        }
        /*
        order=cart.createOrder(order);
        */
        int sum=0;
        Iterator iter=cart.getGoods().keySet().iterator();
        while(iter.hasNext()){
            Integer bookid=(Integer) iter.next();
            Book book=bookDao.getBookByid(bookid);
            sum += ( book.getPrice() ) * ( cart.getGoods().get(bookid) );
            BooksOrder bo=new BooksOrder(book,cart.getGoods().get(bookid));
            order.getBooks().add(bo);
        }
        System.out.println("end Cart.createOrder.\n");
        order.setTot_price(sum);
        System.out.println(order);
        System.out.println("saving order...\n");
        if(orderDao.save(order)){
            session.remove("cart");
            System.out.println(order);
            return order;
        }
        return null;
    }

    @Override
    @Transactional
    public Integer getTotalAmount(){
        return orderDao.getTotalAmount();
    }


    @Override
    @Transactional
    public List<Order> getOrders(){
        return orderDao.getOrders();
    }

    @Override
    @Transactional(readOnly = false)
    public List<Order> getUserOrders(){
        User user=(User) ActionContext.getContext().getSession().get("user");
        List<Order> orders=orderDao.getUserOrders(user.getUserid());
        System.out.println(orders);
        return orders;
    }

    @Override
    @Transactional(readOnly = false)
    public boolean confirmOrder(int orderid){
        return orderDao.changeState(orderid,'Y');
    }

    @Override
    @Transactional(readOnly = false)
    public boolean cancelOrder(int orderid){
        return orderDao.changeState(orderid,'C');

    }

    @Override
    @Transactional(readOnly = false)
    public boolean destroyOrder(int orderid){
        return orderDao.destroyOrder(orderid);

    }

    public OrderDao getOrderDao() {
        return orderDao;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }
    public BookDao getBookDao(){
        return bookDao;
    }
    public void setBookDao(BookDao bookDao){
        this.bookDao=bookDao;
    }
}
