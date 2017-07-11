package entity;



import java.util.HashMap;


/**
 * Created by zzy on 2017/4/23.
 */
public class Cart {
    private HashMap<Integer, Integer> goods;
    //private int totalPrice;

    @Override
    public String toString() {
        return "Cart [goods=" + goods + "]";
    }


    public Cart()
    {
        goods = new HashMap<Integer, Integer>();
        //totalPrice = 0;
    }


    public HashMap<Integer, Integer> getGoods() {
        return goods;
    }
    public void setGoods(HashMap<Integer,Integer> goods){
        this.goods=goods;
    }
    /*
    public double getTotalPrice() {
        calTotalPrice();
        return totalPrice/100;
    }

    public boolean addGoodsInCart(int bookid ,int amount)
    {
        if(goods.containsKey(bookid))
        {
            int oldAmount = goods.get(bookid);
            if(oldAmount + amount > 0)	// the total amount of a certain item should > 0
                goods.put(bookid, oldAmount+amount);
            else
                return false;
        }
        else
            goods.put(bookid, amount);
        return true;
    }

    public boolean removeGoodsFromCart(int bookid)
    {
        goods.remove(bookid);
        return true;
    }

    public double calTotalPrice()
    {
        int sum = 0;
        BookDao bookDao = getBookDaoBean();
        Set<Integer> keys = goods.keySet();
        Iterator<Integer> it = keys.iterator();
        while(it.hasNext())
        {
            Integer bookid = it.next();
            Book book = bookDao.getBookByid(bookid);
            sum += book.getPrice()* goods.get(bookid);
        }
        totalPrice = sum;
        return sum;
    }

    public Order createOrder(Order order) {
        int sum = 0;
        BookDao bookDao = getBookDaoBean();
        Iterator iter = goods.keySet().iterator();
        while(iter.hasNext()) {
            Integer bookid = (Integer) iter.next();
            Book book = bookDao.getBookByid(bookid);
            System.out.println(book);
            sum += book.getPrice() * goods.get(bookid);
            BooksOrder bo = new BooksOrder(book, goods.get(bookid));
            order.getBooks().add(bo);
        }
        System.out.println("end Cart.createOrder.\n");
        order.setTot_price(sum);
        System.out.println(order);

        return order;
    }

    private BookDao getBookDaoBean(){
        ApplicationContext appctx=new ClassPathXmlApplicationContext("classpath:spring-config.xml");
        return appctx.getBean(BookDao.class);
    }
    */
}
