package act;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;
import service.CartService;

/**
 * Created by zzy on 2017/5/4.
 */
public class CartAct extends ActionSupport{
    private Map<String,Object> dataMap=new HashMap<String,Object>();
    @Autowired
    private CartService cartService;
    private int bookid;
    private int amount;

    public Map<String, Object> getDataMap() {
        return dataMap;
    }

    public void setDataMap(Map<String, Object> dataMap) {
        this.dataMap = dataMap;
    }

    public int getBookid() {
        return bookid;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


    public String addToCart(){
        dataMap.clear();
        dataMap.put("success",cartService.addToCart(bookid,amount));
        return "ajax";
    }


    public String cartInfo(){
        dataMap.clear();
        dataMap.put("cart",cartService.cartInfo());
        return "ajax";

    }

    public String changeAmountInCart(){
        int change=Integer.parseInt(((String[]) ActionContext.getContext().getParameters().get("change"))[0]);
        dataMap.clear();
        dataMap.put("success",cartService.changeAmountInCart(bookid,change));
        return "ajax";
    }

    public String removeBookInCart(){
        dataMap.put("success",cartService.removeBookFromCart(bookid));
        return "ajax";

    }
}
