package entity;

/**
 * Created by zzy on 2017/4/19.
 */
public class BooksOrder {
    private Book book;
    private int amount;

    public int getAmount(){
        return amount;
    }
    public void setAmount(int amount){
        this.amount=amount;
    }

    public Book getBook(){
        return book;
    }

    public void setBook(Book book){
        this.book=book;
    }

    @Override
    public String toString() {
        return "BooksOrder [book=" + book + ", amount=" + amount + "]";
    }

    public BooksOrder(Book book, int amount){
        super();
        this.amount=amount;
        this.book=book;
    }
    public BooksOrder(){
        super();
    }
}
