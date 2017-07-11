package dao;

import java.util.List;
import java.util.Set;


import entity.Book;
import entity.Category;

/**
 * Created by zzy on 2017/4/19.
 */
public interface BookDao extends BaseDao{
    List<Book> getBooks();
    Set<Book> getBooksOfCategoryByid(int categoryid);
    Set<Category> getCategoriesOfBookByid(int bookid);
    boolean destroyBook(int bookid);
    Integer getTotalAmount();
    Integer getTotalCategoryAmount();
    List<Category> getAllCategory();
    boolean destroyCategory(int categoryid);
    Category getCategoryByid(int categoryid);
    Book getBookByid(int bookid);
    List<Book> getBooksNameLike(String bookname);


}
