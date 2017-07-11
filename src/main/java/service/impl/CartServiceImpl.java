package service.impl;

import com.opensymphony.xwork2.ActionContext;
import dao.BookDao;
import entity.Book;
import entity.Cart;
import org.springframework.transaction.annotation.Transactional;
import service.CartService;

import java.util.*;

/**
 * Created by zzy on 2017/5/4.
 */
@Transactional(readOnly=true)
public class CartServiceImpl implements CartService{
    private Cart cart;
    private BookDao bookDao;




    @Override
    public boolean addToCart(int bookid,int amount) {
        // get cart object from session or create a cart save into session
        Map session = ActionContext.getContext().getSession();
        if(session.containsKey("cart")) {
            cart = (Cart) session.get("cart");
        } else {
            cart = new Cart();
            session.put("cart", cart);
        }

        // add item into session
         //return cart.addGoodsInCart(bookid, amount);
        if(cart.getGoods().containsKey(bookid)){
            int oldAmount= cart.getGoods().get(bookid);
            if(oldAmount+amount>0){
                cart.getGoods().put(bookid,oldAmount+amount);
                return true;
            }
            else
                return false;
        }
        else{
            cart.getGoods().put(bookid,amount);
            return true;

        }

    }





    public List<Book> cartInfo(){
        Map session = ActionContext.getContext().getSession();
        if(session.containsKey("cart"))
            cart=(Cart) session.get("cart");
        else{
            cart = new Cart();
            session.put("cart",cart);
        }
        HashMap<Integer,Integer> goods=cart.getGoods();
        Set<Integer> keys = goods.keySet();
        Iterator<Integer> it=keys.iterator();
        ArrayList<Book> books = new ArrayList<Book>();
        while(it.hasNext())
        {
            Integer bookid=it.next();
            Book book=bookDao.getBookByid(bookid);
            book.setAmount(goods.get(bookid));
            books.add(book);
            //System.out.println(book);
        }

        return books;

    }




    @Override
    public boolean changeAmountInCart(int bookid,int change){
        Map session=ActionContext.getContext().getSession();
        cart=(Cart) session.get("cart");
        if(cart==null){
            return false;
        }

        if(cart.getGoods().containsKey(bookid)){
            int oldAmount= cart.getGoods().get(bookid);
            if(oldAmount+change>0){
                cart.getGoods().put(bookid,oldAmount+change);
                return true;
            }
            else
                return false;
        }
        else{
            return false;
        }
    }



    @Override
    public boolean removeBookFromCart(int bookid){
        Map session=ActionContext.getContext().getSession();
        cart=(Cart) session.get("cart");
        if(cart==null){
            return false;
        }
        cart.getGoods().remove(bookid);
        return true;

    }

    @Override
    public boolean clearCart(){
        Map session = ActionContext.getContext().getSession();
        if(session.containsKey("cart")){
            session.remove("cart");
            return true;
        }
        else{
            return false;
        }
    }

    public BookDao getBookDao() {
        return bookDao;
    }

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }
}
