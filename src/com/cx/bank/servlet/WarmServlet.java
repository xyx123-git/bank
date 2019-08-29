/*
 * xyx 2019.8.13
 * 管理员操作界面，解冻某一用户的账户
 */

package com.cx.bank.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cx.bank.manager.ManagerImpl;


public class WarmServlet extends HttpServlet{
	public void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");//编码
		ManagerImpl mi=ManagerImpl.getInstance();//通过单例拿到业务层对象
		 String name=req.getParameter("freezers");//拿到要解冻的用户的名字
		 mi.warm(name);//调用业务层的解冻的方法
		 List list=(List) mi.findAllUser();//再调用业务层查找所有用户信息的方法，显示用户当前的状态
		 req.setAttribute("userlist",list);//把查询结果的list集合放在request中，回传给前台页面显示
		 req.getRequestDispatcher("/list.jsp").forward(req, resp);
   }
   public void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
	   doPost(req, resp);
   }
}
