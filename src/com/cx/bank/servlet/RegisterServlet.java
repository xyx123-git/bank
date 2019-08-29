/*
 * xyx 2019.8.13
 * 注册功能的servlet
 */

package com.cx.bank.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cx.bank.manager.ManagerImpl;

public class RegisterServlet extends HttpServlet {
	public void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String name=req.getParameter("name");//拿到表单中注册的用户名
 	   String password=req.getParameter("password");//表单中要注册的密码
 	   ManagerImpl mi=ManagerImpl.getInstance();//通过单例拿到业务层对象
 	   boolean flag=mi.register(name,password);//调用业务层的注册方法，返回注册是否成功的标志
 	   System.out.println(flag);
 	   if(flag) {
 	       req.getRequestDispatcher("/index.jsp").forward(req, resp);//注册成功，跳到登录页面
 	      
 	       }
 		   resp.sendRedirect("Registererror.jsp");   //注册失败，跳到注册出错页面
 		   return;
 	   
    }
    public void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
 	   doPost(req, resp);
    }
}
