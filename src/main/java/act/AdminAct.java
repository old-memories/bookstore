package act;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Created by zzy on 2017/5/11.
 */
public class AdminAct extends ActionSupport {
    public String adminBook(){
        ActionContext.getContext().put("title","Book Admin");
        return "book";
    }
    public String adminUser(){
        ActionContext.getContext().put("title","User Admin");
        return "user";
    }
    public String adminCategory(){
        ActionContext.getContext().put("title","Category Admin");
        return "category";
    }
    public String adminOrder(){
        ActionContext.getContext().put("title","Order Admin");
        return "order";
    }


}
