/*
 * xyx 2019.8.13
 * 存款功能的servlet
 */

package com.cx.bank.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cx.bank.manager.ManagerImpl;
import com.cx.bank.model.UserBean;
import com.cx.bank.util.InvalidDepositException;

public class DepositServlet extends HttpServlet{
	 public void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
		 req.setCharacterEncoding("UTF-8");//编码
		 ManagerImpl mi=ManagerImpl.getInstance();//通过单例拿到业务层对象
		 UserBean ub=new UserBean();//创建userbean对象存放用户信息，并进行传递
		 String name=(String) req.getSession().getAttribute("name");//拿到当前登陆成功的用户名
		 ub.setName(name);
		 int flag=mi.checklog(ub);//查询当前用户的账号是否被冻结，如果被冻结则返回存款界面，显示冻结信息
		 if(flag==0) {//如果账户没有被冻结则尽心存款操作
		 String save=req.getParameter("deposit");//拿到表单中的存款金额
		 try {
			double current=mi.deposit(ub, save);//调用业务层存款方法，返回存款后的账户余额
			req.setAttribute("current",current);//把余额设到request中，传递到前台页面显示
			req.getRequestDispatcher("depositSucceed.jsp").forward(req, resp);//跳转到存款成功界面，显示余额
		} catch (InvalidDepositException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 }
		 else {
			    req.setAttribute("information","您的账户已被冻结");
				req.getRequestDispatcher("deposit.jsp").forward(req, resp);
		 }
     }
     public void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
  	   doPost(req, resp);
     }
}
