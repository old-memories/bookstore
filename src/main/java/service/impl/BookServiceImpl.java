package service.impl;

import com.opensymphony.xwork2.ActionContext;
import dao.BookDao;
import entity.Book;
import entity.Category;
import org.springframework.transaction.annotation.Transactional;
import service.BookService;
import util.MD5Generator;

import java.util.*;

/**
 * Created by zzy on 2017/5/4.
 */
@Transactional(readOnly=true)
public class BookServiceImpl implements BookService {
    private BookDao bookDao;
    @Override
    @Transactional(readOnly=true)
    public Book showBookByid(int bookid){
        return bookDao.getBookByid(bookid);
    }

    @Override
    @Transactional(readOnly=true)
    public List<Book> getBooks(){
        List<Book> books = bookDao.getBooks();
       return books;
    }

    @Override
    @Transactional(readOnly=true)
    public List<Book> showBooksOfCategoryByid(int categoryid){
        Set<Book> b =  bookDao.getBooksOfCategoryByid(categoryid);
        if(b==null)
            return null;
        List<Book> books = new ArrayList<Book>(b);
        return books;
    }


    @Override
    @Transactional(readOnly=true)
    public List<Category> getCategoriesOfBookByid(int bookid){
        Set<Category> c =  bookDao.getCategoriesOfBookByid(bookid);
        if(c==null)
            return null;
        List<Category> categories = new ArrayList<Category>(c);
        return categories;
    }


    @Override
    @Transactional(readOnly=false)
    public boolean saveBook(Book book){
        return bookDao.save(book);
    }



    @Override
    @Transactional(readOnly=false)
    public Book setCategoryByCategoryName(Book book,String categoryName){
        String[] categoryArr = categoryName.split(",");
        Set<Category> categories = new HashSet<Category>();
        for(int i=0;i<categoryArr.length;i++){
            categoryArr[i] = categoryArr[i].trim();
            System.out.println(categoryArr[i]);
            categories.add(getCategoryByid(Integer.parseInt(categoryArr[i])));

        }
        book.setCategory(categories);
        System.out.println("categories:\n"+book.getCategory());
        return book;
    }


    @Override
    @Transactional(readOnly=false)
    public boolean updateBook(Book book){
        return bookDao.update(book);
    }

    @Override
    @Transactional(readOnly=false)
    public boolean destroyBook(int bookid){
        return bookDao.destroyBook(bookid);
    }

    @Override
    @Transactional(readOnly=true)
    public List<Book> getBooksNameLike(String bookname){
        List<Book> books= bookDao.getBooksNameLike(bookname);
        return books;
    }

    @Override
    @Transactional(readOnly=true)
    public Integer getTotalAmount(){
        return bookDao.getTotalAmount();
    }


    @Override
    @Transactional(readOnly=true)
    public Integer getTotalCategoryAmount(){
        return bookDao.getTotalCategoryAmount();
    }


    @Override
    @Transactional(readOnly = true)
    public List<Category> getAllCategory(){
        List<Category> category = bookDao.getAllCategory();
        return category;
    }

    @Override
    @Transactional(readOnly = true)
    public Category getCategoryByid(int categoryid){
        return bookDao.getCategoryByid(categoryid);
    }


    @Override
    @Transactional(readOnly=false)
    public boolean saveCategory(Category category){
        return bookDao.save(category);
    }

    @Override
    @Transactional(readOnly=false)
    public boolean updateCategory(Category category){
        return bookDao.update(category);
    }

    @Override
    @Transactional(readOnly=false)
    public boolean destroyCategory(int categoryid){
        return bookDao.destroyCategory(categoryid);
    }

    public void setBookDao(BookDao bookDao){
        this.bookDao=bookDao;
    }
    public BookDao getBookDao(){
        return bookDao;
    }

}
