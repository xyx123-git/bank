/*
 * xyx 2019.8.13
 * 转账功能的servlet
 */

package com.cx.bank.servlet;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cx.bank.manager.ManagerImpl;
import com.cx.bank.model.UserBean;


public class TransferServlet extends HttpServlet {
	public void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
		 req.setCharacterEncoding("UTF-8");//编码
		 ManagerImpl mi=ManagerImpl.getInstance();//通过单例拿到业务层对象
		 UserBean ub=new UserBean();//创建userbean对象存放用户信息，并进行传递
		 String name=(String)req.getSession().getAttribute("name");//从当前用户的session中拿到登陆成功的用户名
		 String money=req.getParameter("money");//拿到表单里转账金额
		 String towhom=req.getParameter("towhom");//拿到表单中想要给谁转账的名字
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
			}else {
				req.setAttribute("information","转账失败，该账户已被冻结");//如果当前用户的账号被冻结则返回转账界面，显示账户冻结信息
				req.getRequestDispatcher("transfer.jsp").forward(req, resp);
				return;
			}
			}else {//如果用户余额小于转账金额，则返回转账界面，显示余额不足的信息
				  req.setAttribute("information","您的余额不足");
				  req.getRequestDispatcher("transfer.jsp").forward(req, resp);
			}
			  }catch(Exception e) {
				e.getStackTrace();
			} 
		  }
		 else {
			 req.setAttribute("information","您的账户已被冻结");
			 req.getRequestDispatcher("transfer.jsp").forward(req, resp);
			 return;
		 }
		
    }
    public void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
 	   doPost(req, resp);
    }
}
