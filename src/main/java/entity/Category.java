package entity;

import java.util.Set;

/**
 * Created by zzy on 2017/6/6.
 */
public class Category {
    private int categoryid;
    private String name;
    private Set<Book> books = null;

    public Category(){
        super();
    }
    public Category(int categoryid,String name,Set<Book> books){
        super();
        this.categoryid=categoryid;
        this.name=name;
        this.books=books;
    }

    public Set<Book> getBooks() {
        return books;
    }
    public void setBooks(Set<Book> books) {
        this.books = books;
    }
    public int getCategoryid() {
        return categoryid;
    }
    public void setCategoryid(int categoryid) {
        this.categoryid = categoryid;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category [categoryid=" + categoryid + ", name=" + name + "]";
    }
}
