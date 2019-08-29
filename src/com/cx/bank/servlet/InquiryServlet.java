/*
 * xyx 2019.8.13
 * 查询余额的servlet控制层  
 *  结果参数：current
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

public class InquiryServlet extends HttpServlet {
	public void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");//加编码方式
		ManagerImpl mi=ManagerImpl.getInstance();//通过单例拿到业务层对象
		 UserBean ub=new UserBean();//创建bean对象存放当前用户的名字，进行查询操作
		 String name=(String) req.getSession().getAttribute("name");//从登陆成功的用户的session中拿到用户名
		 ub.setName(name);
		
			double current=mi.inquiry(ub);//查询当前用户的余额
			req.setAttribute("current",current);//把余额设到request内置对象账户，用于在查询页面显示查询结果
			req.getRequestDispatcher("inquiry.jsp").forward(req, resp);//服务端跳转
		
    }
    public void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
 	   doPost(req, resp);
    }
}
