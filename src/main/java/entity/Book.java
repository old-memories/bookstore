package entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by zzy on 2017/4/19.
 */
public class Book {
    private int bookid;
    private int price;
    private int amount;
    private String author;
    private String bookname;
    private String imageid;
    private Set<Category> category = new HashSet<Category>();
    public Book(){
        super();
    }
    public Book(int bookid, String bookname,int amount, String author, int price, Set<Category> category) {
        super();
        this.bookid = bookid;
        this.bookname = bookname;
        this.amount=amount;
        this.author = author;
        this.price = price;
        this.category=category;
    }


    @Override
    public String toString() {
        return "Book [bookid=" +bookid+", bookname=" + bookname+", amount=" + amount
                + ", author=" + author + ", price=" +price+ ", category="+category+"]";
    }

    public int getBookid() {return bookid;}
    public void setBookid(int bookid) {this.bookid = bookid;}
    public String getBookname() {
        return bookname;
    }
    public void setBookname(String bookname) {
        this.bookname = bookname;
    }
    public int getAmount(){
        return amount;
    }
    public void setAmount(int amount){
        this.amount=amount;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public int getPrice() {return price;}
    public void setPrice(int price) { this.price=price;}
    public String getImageid() {return imageid;}
    public void setImageid(String imageid) { this.imageid=imageid;}
    public Set<Category> getCategory(){
        return category;
    }
    public void setCategory(Set<Category> category){
        this.category=category;
    }

}
