/*
 * xyx 2019.8.13
 * 登录功能的servlet
 */
package com.cx.bank.servlet;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cx.bank.manager.ManagerImpl;

public class LoginServlet extends HttpServlet{
       public void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
    	   req.setCharacterEncoding("UTF-8");
    	   String name=req.getParameter("username");//表单中的用户名
    	   String password=req.getParameter("password");//用户密码
    	   if("xyx".equals(name)&&"123".equals(password)) {//管理员登录
    		   req.getRequestDispatcher("login.jsp").forward(req, resp);//跳转到管理员界面
    	   }
    	   ManagerImpl mi=ManagerImpl.getInstance();//通过单例拿到业务层对象
    	   boolean flag=mi.login(name,password);//调用业务层登录方法，返回是否登录成功
    	   System.out.println(flag);
    	   if(flag) {
    		   req.getSession().setAttribute("name",name);//登录成功，往该用户对应的session中设定用户名和密码
    		   req.getSession().setAttribute("password",password);
    	       req.getRequestDispatcher("userlogin.jsp").forward(req, resp);//跳转到用户界面
    	       }
    	   else {
    		   resp.sendRedirect("error.jsp"); //登录失败，跳转到失败页面  
    		   return;
    	   }
    	   
       }
       public void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
    	   doPost(req, resp);
       }
}
