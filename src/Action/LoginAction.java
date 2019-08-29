package Action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.cx.bank.manager.ManagerImpl;
import com.cx.bank.model.UserBean;

public class LoginAction extends Action{
	 public ActionForward execute(ActionMapping mapping,ActionForm actionform,HttpServletRequest req,HttpServletResponse resp) throws IOException{
	    	req.setCharacterEncoding("UTF-8");
	    	UserBean bean=(UserBean)actionform;
	    	String name=bean.getName();//表单中的用户名
	    	String password=bean.getPassword();//用户密码
	    	   if("xyx".equals(name)&&"123".equals(password)) {//管理员登录
	    		   //req.getRequestDispatcher("login.jsp").forward(req, resp);//跳转到管理员界面
	    	         return mapping.findForward("login");
	    	   }
	    	   ManagerImpl mi=ManagerImpl.getInstance();//通过单例拿到业务层对象
	    	   boolean flag=mi.login(name,password);//调用业务层登录方法，返回是否登录成功
	    	   System.out.println(flag);
	    	   if(flag) {
	    		   req.getSession().setAttribute("name",name);//登录成功，往该用户对应的session中设定用户名和密码
	    		   req.getSession().setAttribute("password",password);
	    	      // req.getRequestDispatcher("userlogin.jsp").forward(req, resp);//跳转到用户界面
	    	       return mapping.findForward("userlogin");
	    	   }
	    	   else {
	    		  // resp.sendRedirect("error.jsp"); //登录失败，跳转到失败页面  
	    		   
	    		   return mapping.findForward("error");
	    	   }
      }
}
