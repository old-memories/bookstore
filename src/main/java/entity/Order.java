package entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by zzy on 2017/4/19.
 */
public class Order {
    private int orderid;
    private User user;
    private  int tot_price;
    private char pucharsed;
    public Order() {}
    private Set<BooksOrder> books=new HashSet<BooksOrder>(0);
    @Override
    public String toString() {
        return "Order [orderid=" + orderid + ", user=" + user + ", books=" + books + ", tot_price="+tot_price+", pucharsed="+pucharsed+ "]";
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Set<BooksOrder> getBooks() {
        return books;
    }

    public void setBooks(Set<BooksOrder> books) {
        this.books = books;
    }

    public int getTot_price() {
        return tot_price;
    }

    public void setTot_price(int tot_price) {
        this.tot_price = tot_price;
    }
    public void setPucharsed(char pucharsed){this.pucharsed=pucharsed;}
    public char getPucharsed(){return pucharsed;}
}

