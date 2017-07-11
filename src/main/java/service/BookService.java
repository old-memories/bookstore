package service;

import entity.Book;
import entity.Category;

import java.util.List;

/**
 * Created by zzy on 2017/5/4.
 */
public interface BookService {
    Book showBookByid(int bookid);
    List<Book> getBooksNameLike(String bookname);
    List<Book> getBooks();
    Integer getTotalAmount();
    Integer getTotalCategoryAmount();
    List<Category> getAllCategory();
    List<Book> showBooksOfCategoryByid(int categoryid);
    List<Category> getCategoriesOfBookByid(int bookid);
    Category getCategoryByid(int categoryid);
    Book setCategoryByCategoryName(Book book,String categoryName);
    boolean saveCategory(Category category);
    boolean updateCategory(Category Category);
    boolean destroyCategory(int categoryid);
    boolean saveBook(Book book,String myFileFileName);
    boolean updateBook(Book book);
    boolean destroyBook(int bookid);

}
