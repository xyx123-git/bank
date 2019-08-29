package Action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.cx.bank.manager.ManagerImpl;
import com.cx.bank.model.UserBean;

public class RegisterAction extends Action{
	public ActionForward execute(ActionMapping mapping,ActionForm actionform,HttpServletRequest req,HttpServletResponse resp) throws IOException {
    	req.setCharacterEncoding("UTF-8");
    	UserBean bean=(UserBean)actionform;
       //String name=req.getParameter("name");//拿到表单中注册的用户名
    	String name=bean.getName();
  	  // String password=req.getParameter("password");//表单中要注册的密码
    	String password=bean.getPassword();
  	   ManagerImpl mi=ManagerImpl.getInstance();//通过单例拿到业务层对象
  	   boolean flag=mi.register(name,password);//调用业务层的注册方法，返回注册是否成功的标志
  	   System.out.println(flag);
  	   if(flag) {
  	      // req.getRequestDispatcher("/index.jsp").forward(req, resp);//注册成功，跳到登录页面
  	      return mapping.findForward("index");
  	       }
  		  // resp.sendRedirect("Registererror.jsp");   //注册失败，跳到注册出错页面
  		 return mapping.findForward("error");
	}
}
