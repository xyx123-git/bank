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

import com.cx.bank.manager.ManagerImpl;

public class ManagerAction extends DispatchAction{
	public ActionForward unspecified(ActionMapping mapping,ActionForm actionform,HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
    	//req.getRequestDispatcher("/index.jsp").forward(req, resp);
    	return mapping.findForward("index");
    
    }
	
	
	public ActionForward beforeFreeze(ActionMapping mapping,ActionForm actionform,HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
    	//req.getRequestDispatcher("/index.jsp").forward(req, resp);
		ManagerImpl mi=ManagerImpl.getInstance();
		 List list=(List) mi.findAllUser();
		 req.setAttribute("names",list);
		 req.getRequestDispatcher("/WEB-INF/Freeze.jsp").forward(req, resp);
		 return null;
    }
	
	public ActionForward findAllFreezers(ActionMapping mapping,ActionForm actionform,HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
    	//req.getRequestDispatcher("/index.jsp").forward(req, resp);
		ManagerImpl mi=ManagerImpl.getInstance();//通过单例拿到业务层对象
		 List list=(List) mi.findAllFreezer();//调用业务层查找所有冻结者的方法，返回的是存放所有冻结用户的名字的list集合
		 req.setAttribute("freezers",list);//把集合存放在request内置对象中，返回给前台页面进行显示
		 System.out .println(list);
		 req.getRequestDispatcher("beforeWarm.jsp").forward(req, resp);
		 return null;
    }
	
	public ActionForward findAllLogs(ActionMapping mapping,ActionForm actionform,HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
    	//req.getRequestDispatcher("/index.jsp").forward(req, resp);
		ManagerImpl mi=ManagerImpl.getInstance();//通过单例拿到业务层对象
		 List list=(List) mi.findAllTag();//调用业务层的方法
		 req.setAttribute("tags",list);//把查询返回的list集合放在request中传给前台页面显示
		 req.getRequestDispatcher("showAllTags.jsp").forward(req, resp);
		 return null;
    
    }
	
	public ActionForward freeze(ActionMapping mapping,ActionForm actionform,HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
    	//req.getRequestDispatcher("/index.jsp").forward(req, resp);
		ManagerImpl mi=ManagerImpl.getInstance();//通过单例拿到业务层对象
		 String name=req.getParameter("names");//拿到要冻结的用户的名字
		 boolean flag=mi.freeze(name);//调用业务层的冻结的额方法
		 List list=(List) mi.findAllUser();//调用业务层查询所有用户的信息的方法，返回存放所有用户信息的list集合
		 req.setAttribute("userlist",list);//把包含查询结果的list集合放在request中，传回给前台页面显示
		 req.getRequestDispatcher("/list.jsp").forward(req, resp);
		 return null;
    
    }
	
	
	public ActionForward warm(ActionMapping mapping,ActionForm actionform,HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
    	//req.getRequestDispatcher("/index.jsp").forward(req, resp);
		ManagerImpl mi=ManagerImpl.getInstance();//通过单例拿到业务层对象
		 String name=req.getParameter("freezers");//拿到要解冻的用户的名字
		 mi.warm(name);//调用业务层的解冻的方法
		 List list=(List) mi.findAllUser();//再调用业务层查找所有用户信息的方法，显示用户当前的状态
		 req.setAttribute("userlist",list);//把查询结果的list集合放在request中，回传给前台页面显示
		 req.getRequestDispatcher("/list.jsp").forward(req, resp);
         return null;
    }
}
