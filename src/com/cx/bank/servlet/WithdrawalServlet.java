/*
 * xyx 2019.8.13
 * 取款的servlet
 */
package com.cx.bank.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cx.bank.manager.ManagerImpl;
import com.cx.bank.model.UserBean;
import com.cx.bank.util.AccountOverDrawnException;
import com.cx.bank.util.InvalidDepositException;

public class WithdrawalServlet extends HttpServlet {
	 public void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
		 req.setCharacterEncoding("UTF-8");//编码
		 ManagerImpl mi=ManagerImpl.getInstance();//通过单例拿到业务层对象
		 UserBean ub=new UserBean();//创建userbean对象存放用户信息，便于传递
		 String name=(String) req.getSession().getAttribute("name");//拿到登录成功的用户名
		 ub.setName(name);//把该用户名存放在userbean中
		 int flag=mi.checklog(ub);//先查询当前用户是否被冻结，如果查询结果是1，表明当前用户被冻结则返回取款页面，提示冻结信息
		 if(flag==0) {//如果查询结果是0，表明当前用户没有被冻结，可以进行取款操作
		 String leave=req.getParameter("leave");//拿到表单里要取款的金额
		 double current1=mi.inquiry(ub);//查询当前用户的余额，如果余额大于等于要取款的金额，则可以进行取款操作
		 double leave1=Double.parseDouble(leave);
		 if(current1>=leave1) {
			double current;//存放取款后的余额
			try {
				current = mi.withdrawals(ub,leave);//调用业务层取款方法
				req.setAttribute("current",current);//把余额放在request中，传到取款成功页面进行显示
				req.getRequestDispatcher("withdrawalSucceed.jsp").forward(req, resp);
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
		 }
		 }else {
			 req.setAttribute("information","您的账户已被冻结，请至柜台处理");
			 req.getRequestDispatcher("withdrawal.jsp").forward(req, resp);//取款失败跳到取款界面，显示错误信息
		 }	
     }
     public void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
  	   doPost(req, resp);
     }
}
