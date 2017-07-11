package act.interceptor;
import java.util.Map;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import entity.User;
/**
 * Created by zzy on 2017/4/22.
 */
public class isAdmin extends AbstractInterceptor {
    @Override
    public String intercept(ActionInvocation ai) throws Exception {
        Map session=ai.getInvocationContext().getSession();
        System.out.println("auth check:");
        if(session.containsKey("user"))
        {
            User user=(User) session.get("user");
            if(user.getRole().equals("admin")){
                System.out.println("admin\n");
                return ai.invoke();
            }
            else
            {
                System.out.println("403\n");
                return "403";
            }
        }
        System.out.println("login\n");

        return Action.LOGIN;
    }
}
