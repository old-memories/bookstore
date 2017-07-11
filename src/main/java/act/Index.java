package act;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ActionContext;

import java.util.List;
import dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import service.BookService;

/**
 * Created by zzy on 2017/4/18.
 */
public class Index extends ActionSupport{
    @Autowired
    private BookService bookService;

    @Override
    public String execute() throws Exception {
        return "success";
    }
    /*
    public String index(){
//		System.out.println("in index\n");
        UserDao userDao = new UserDao();
        List users = userDao.getUsers(1,1);
        ActionContext.getContext().put("users", users);
//		System.out.println(books);
        return SUCCESS;
    }
    */
    public String index(){
        ActionContext.getContext().put("category",bookService.getAllCategory());
        ActionContext.getContext().put("books", bookService.getBooks());
        return "success";
    }
}
