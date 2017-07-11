package dao.impl;

import dao.BookDao;
import entity.Book;
import entity.Category;

import java.util.List;
import java.util.Set;

/**
 * Created by zzy on 2017/6/15.
 */
public class BookDaoImpl extends BaseDaoImpl implements BookDao{
    @Override
    public List<Book> getBooks(){
        try {
            String hql = "from Book";
            List<Book> books = getSessionFactory().getCurrentSession().createQuery(hql).list();
            return books;
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Set<Book> getBooksOfCategoryByid(int categoryid){
        try {
            String hql = "select c from Category c join fetch c.books  where c.categoryid = :id";
            List rs = getSessionFactory().getCurrentSession().createQuery(hql).setParameter("id", categoryid).list();
            if(rs.size() == 0)
                return null;
            Set books = ((Category)rs.get(0)).getBooks();
            return books;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Set<Category> getCategoriesOfBookByid(int bookid){
        try {
            String hql = "select b from Book b join fetch b.category  where b.bookid = :id";
            List rs = getSessionFactory().getCurrentSession().createQuery(hql).setParameter("id", bookid).list();
            if(rs.size() == 0)
                return null;
            Set categories = ((Book)rs.get(0)).getCategory();
            return categories;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean destroyBook(int bookid) {
        try {
            Book book = getSessionFactory().getCurrentSession().load(Book.class, bookid);
            getSessionFactory().getCurrentSession().delete(book);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }




    @Override
    public Integer getTotalAmount(){
        try {
            String hql = "select count(*) from Book";
            int num = ((Long)getSessionFactory().getCurrentSession().createQuery(hql).iterate().next()).intValue();
            return num;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public Integer getTotalCategoryAmount(){
        try {
            String hql = "select count(*) from Category ";
            int num = ((Long)getSessionFactory().getCurrentSession().createQuery(hql).iterate().next()).intValue();
            return num;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Category> getAllCategory(){
        try{
            String hql = "from Category";
            List<Category> category = getSessionFactory().getCurrentSession().createQuery(hql).list();
            return category;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean destroyCategory(int categoryid) {
        try {
            Category category = getSessionFactory().getCurrentSession().load(Category.class, categoryid);
            getSessionFactory().getCurrentSession().delete(category);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Category getCategoryByid(int categoryid){
        try {
            String hql="from Category where categoryid = :id";
            List list=getSessionFactory().getCurrentSession().createQuery(hql).setParameter("id",categoryid).list();
            if(list.size()==1)
                return (Category)list.get(0);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Book getBookByid(int bookid){
        try {
            String hql="from Book where bookid = :id";
            List list=getSessionFactory().getCurrentSession().createQuery(hql).setParameter("id",bookid).list();
            if(list.size()==1)
                return (Book)list.get(0);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Book> getBooksNameLike(String bookname){
        try {
            String hql="from Book b where b.bookname like :name";
            List books=getSessionFactory().getCurrentSession().createQuery(hql).setParameter("name","%"+bookname+"%").list();
            if(books.size()>0)
                return books;
            else
                return null;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }


}
