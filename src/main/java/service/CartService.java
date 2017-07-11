package service;

import entity.Book;

import java.util.List;

/**
 * Created by zzy on 2017/5/4.
 */
public interface CartService {
    boolean addToCart(int bookid, int amount);
    List<Book> cartInfo();
    boolean changeAmountInCart(int bookid, int change);
    boolean removeBookFromCart(int bookid);
    boolean clearCart();

}
