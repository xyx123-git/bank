package Action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.taglibs.standard.lang.jstl.test.Bean1;

import com.cx.bank.manager.ManagerImpl;
import com.cx.bank.model.UserBean;

import com.cx.bank.util.AccountOverDrawnException;
import com.cx.bank.util.InvalidDepositException;

public class UserAction extends DispatchAction{
	 public ActionForward execute(ActionMapping mapping,ActionForm actionform,HttpServletRequest req,HttpServletResponse resp) throws Exception {
	    	req.setCharacterEncoding("UTF-8");
	    	if(req.getSession().getAttribute("name")==null) {
	    		//req.getRequestDispatcher("/index.jsp").forward(req, resp);
	        	return mapping.findForward("index");
	    	}
	    	return super.execute(mapping, actionform, req, resp);
	    	
	 }
    public ActionForward unspecified(ActionMapping mapping,ActionForm actionform,HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
    	//req.getRequestDispatcher("/index.jsp").forward(req, resp);
    	return mapping.findForward("index");
    
    }
    public ActionForward deposit(ActionMapping mapping,ActionForm actionform,HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
    	 req.setCharacterEncoding("UTF-8");
    	 ManagerImpl mi=ManagerImpl.getInstance();//通过单例拿到业务层对象
		 //UserBean ub=new UserBean();//创建userbean对象存放用户信息，并进行传递
    	 UserBean bean=(UserBean)actionform;
		 String name=(String) req.getSession().getAttribute("name");//拿到当前登陆成功的用户名
		 bean.setName(name);
		 
		 int flag=mi.checklog(bean);//查询当前用户的账号是否被冻结，如果被冻结则返回存款界面，显示冻结信息
		 if(flag==0) {//如果账户没有被冻结则尽心存款操作
		 //String save=req.getParameter("deposit");//拿到表单中的存款金额
		 String save=bean.getDeposit(); 
		 try {
			double current=mi.deposit(bean, save);//调用业务层存款方法，返回存款后的账户余额
			req.setAttribute("current",current);//把余额设到request中，传递到前台页面显示
			req.getRequestDispatcher("/depositSucceed.jsp").forward(req, resp);//跳转到存款成功界面，显示余额
			return null;
		} catch (InvalidDepositException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 }
		 else {
			    req.setAttribute("information","您的账户已被冻结");
				req.getRequestDispatcher("/deposit.jsp").forward(req, resp);
				return null;
		 }
		return null;
    }
    
    
    
    public ActionForward withdrawal(ActionMapping mapping,ActionForm actionform,HttpServletRequest req,HttpServletResponse resp) throws Exception {
   	 req.setCharacterEncoding("UTF-8");
   	 ManagerImpl mi=ManagerImpl.getInstance();//通过单例拿到业务层对象
	 //UserBean ub=new UserBean();//创建userbean对象存放用户信息，便于传递
   	 UserBean ub=(UserBean)actionform;
	 String name=(String) req.getSession().getAttribute("name");//拿到登录成功的用户名
	 ub.setName(name);//把该用户名存放在userbean中
	 int flag=mi.checklog(ub);//先查询当前用户是否被冻结，如果查询结果是1，表明当前用户被冻结则返回取款页面，提示冻结信息
	 if(flag==0) {//如果查询结果是0，表明当前用户没有被冻结，可以进行取款操作
	 //String leave=req.getParameter("leave");//拿到表单里要取款的金额
		 
     String leave=ub.getLeave();	 
	 double current1=mi.inquiry(ub);//查询当前用户的余额，如果余额大于等于要取款的金额，则可以进行取款操作
	 double leave1=Double.parseDouble(leave);
	 if(current1>=leave1) {
		double current;//存放取款后的余额
		try {
			current = mi.withdrawals(ub,leave);//调用业务层取款方法
			req.setAttribute("current",current);//把余额放在request中，传到取款成功页面进行显示
			req.getRequestDispatcher("withdrawalSucceed.jsp").forward(req, resp);
			return null;
		} catch (AccountOverDrawnException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidDepositException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }else {//如果当前用户的余额小于取款金额，则返回取款页面，提示余额不足信息
		 req.setAttribute("information","您的余额不足");
		 req.getRequestDispatcher("withdrawal.jsp").forward(req, resp);//取款失败跳到取款界面，显示错误信息
		 return null;
	 }
	 }else {
		 req.setAttribute("information","您的账户已被冻结，请至柜台处理");
		 req.getRequestDispatcher("withdrawal.jsp").forward(req, resp);//取款失败跳到取款界面，显示错误信息
		 return null;
	 }
	return null;	
    }
    
    public ActionForward inquiry(ActionMapping mapping,ActionForm actionform,HttpServletRequest req,HttpServletResponse resp) throws Exception {
      	 req.setCharacterEncoding("UTF-8");
      	 ManagerImpl mi=ManagerImpl.getInstance();//通过单例拿到业务层对象
		 //UserBean ub=new UserBean();//创建bean对象存放当前用户的名字，进行查询操作
      	 UserBean ub=(UserBean)actionform;
		 String name=(String) req.getSession().getAttribute("name");//从登陆成功的用户的session中拿到用户名
		 ub.setName(name);
		
			double current=mi.inquiry(ub);//查询当前用户的余额
			req.setAttribute("current",current);//把余额设到request内置对象账户，用于在查询页面显示查询结果
			req.getRequestDispatcher("inquiry.jsp").forward(req, resp);//服务端跳转
			return null;
    }
    
    
    public ActionForward findLog(ActionMapping mapping,ActionForm actionform,HttpServletRequest req,HttpServletResponse resp) throws Exception {
     	 req.setCharacterEncoding("UTF-8");
     	 ManagerImpl mi=ManagerImpl.getInstance();//通过单例拿到业务层对象
		 //UserBean ub=new UserBean();//创建userbean对象存放用户信息，并传递
     	 UserBean ub=(UserBean)actionform;
		 String name=(String) req.getSession().getAttribute("name");//拿到当前登陆成功的用户名
		 ub.setName(name);
		 
	     List list=(List) mi.findAll(ub);//调用业务层的查找明细的方法
			req.setAttribute("logs",list);//把返回的list集合放在request中传递给前台页面显示
			req.getRequestDispatcher("showLogs.jsp").forward(req, resp);
			return null;
    }
    
    public ActionForward transfer(ActionMapping mapping,ActionForm actionform,HttpServletRequest req,HttpServletResponse resp) throws Exception {
    	 req.setCharacterEncoding("UTF-8");
    	 ManagerImpl mi=ManagerImpl.getInstance();//通过单例拿到业务层对象
		// UserBean ub=new UserBean();//创建userbean对象存放用户信息，并进行传递
    	 UserBean ub=(UserBean)actionform;
		 String name=(String)req.getSession().getAttribute("name");//从当前用户的session中拿到登陆成功的用户名
		 //String money=req.getParameter("money");//拿到表单里转账金额
		 //String towhom=req.getParameter("towhom");//拿到表单中想要给谁转账的名字
		 
		 String money=ub.getMoney();
		 String towhom=ub.getTowhom();
		 ub.setName(name);
		 int flag1=mi.checklog(ub);//查询当前用户是否被冻结，如果当前用户的账号未被冻结，则能进行转账
		 if(flag1==0) {
		  try {
			double money1=Double.parseDouble(money);
			double current1=mi.inquiry(ub);//查询用户当前的账户余额，如果余额大于等于转账金额，则可以进行转账操作
			if(current1>=money1) {
			
			boolean flag=mi.transfer(name,towhom,money);//调用业务层转账的方法，返回是否转账成功的标志
		
			if(flag) {
			
			double current=mi.inquiry(ub);//查询当前用户转账后的余额
			System.out.println(current);
			req.setAttribute("current1",current);
			req.getRequestDispatcher("transferSucceed.jsp").forward(req, resp);
			return null;
			}else {
				req.setAttribute("information","转账失败，该账户已被冻结");//如果当前用户的账号被冻结则返回转账界面，显示账户冻结信息
				req.getRequestDispatcher("transfer.jsp").forward(req, resp);
				return null;
			}
			}else {//如果用户余额小于转账金额，则返回转账界面，显示余额不足的信息
				  req.setAttribute("information","您的余额不足");
				  req.getRequestDispatcher("transfer.jsp").forward(req, resp);
				  return null;
			}
			  }catch(Exception e) {
				e.getStackTrace();
			} 
		  }
		 else {
			 req.setAttribute("information","您的账户已被冻结");
			 req.getRequestDispatcher("transfer.jsp").forward(req, resp);
			 return null;
		 }
		return null;
    }
    
    
    
    public ActionForward exit(ActionMapping mapping,ActionForm actionform,HttpServletRequest req,HttpServletResponse resp) throws Exception {
   	 req.setCharacterEncoding("UTF-8");
   
	resp.sendRedirect("logout.jsp");//点击退出按钮时，返回登录界面
	return null;
    }
}
