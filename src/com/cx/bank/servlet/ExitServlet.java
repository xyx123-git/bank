/*
 * xyx 2019.8.13
 * 退出登录功能
 */

package com.cx.bank.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import com.cx.bank.manager.ManagerImpl;
import com.cx.bank.model.UserBean;

public class ExitServlet extends HttpServlet{
	public void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
			resp.sendRedirect("logout.jsp");//点击退出按钮时，返回登录界面
  	     //mi.exitSystem(name);//调用
		
   }
   public void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
	   doPost(req, resp);
   }
}
