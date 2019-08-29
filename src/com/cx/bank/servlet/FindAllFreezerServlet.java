/*
 * xyx 2019.8.13
 * 查询所有账户被冻结的用户的名字
 */

package com.cx.bank.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cx.bank.manager.ManagerImpl;


public class FindAllFreezerServlet extends HttpServlet{
	public void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");//编码
		ManagerImpl mi=ManagerImpl.getInstance();//通过单例拿到业务层对象
		 List list=(List) mi.findAllFreezer();//调用业务层查找所有冻结者的方法，返回的是存放所有冻结用户的名字的list集合
		 req.setAttribute("freezers",list);//把集合存放在request内置对象中，返回给前台页面进行显示
		 System.out .println(list);
		 req.getRequestDispatcher("beforeWarm.jsp").forward(req, resp);
   }
   public void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
	   doPost(req, resp);
   }
}
