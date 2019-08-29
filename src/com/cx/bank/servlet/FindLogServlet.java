/*
 * xyx 2019.8.13
 * 找到当前用户的所有操作明细的servlet
 * 返回的是存放用户所有操作明细的list集合
 */

package com.cx.bank.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cx.bank.manager.ManagerImpl;
import com.cx.bank.model.UserBean;


public class FindLogServlet extends HttpServlet {
	public void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");//编码
		ManagerImpl mi=ManagerImpl.getInstance();//通过单例拿到业务层对象
		 UserBean ub=new UserBean();//创建userbean对象存放用户信息，并传递
		 String name=(String) req.getSession().getAttribute("name");//拿到当前登陆成功的用户名
		 ub.setName(name);
	     List list=(List) mi.findAll(ub);//调用业务层的查找明细的方法
			req.setAttribute("logs",list);//把返回的list集合放在request中传递给前台页面显示
			req.getRequestDispatcher("showLogs.jsp").forward(req, resp);
		
   }
   public void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
	   doPost(req, resp);
   }
}
