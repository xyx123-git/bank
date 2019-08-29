/*
 * xyx 2019.8.13
 * 管理员操作界面，冻结用户账户功能的servlet
 */

package com.cx.bank.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cx.bank.manager.ManagerImpl;


public class FreezeServlet extends HttpServlet{
	public void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");//编码
		ManagerImpl mi=ManagerImpl.getInstance();//通过单例拿到业务层对象
		 String name=req.getParameter("names");//拿到要冻结的用户的名字
		 boolean flag=mi.freeze(name);//调用业务层的冻结的额方法
		 List list=(List) mi.findAllUser();//调用业务层查询所有用户的信息的方法，返回存放所有用户信息的list集合
		 req.setAttribute("userlist",list);//把包含查询结果的list集合放在request中，传回给前台页面显示
		 req.getRequestDispatcher("/list.jsp").forward(req, resp);
    }
    public void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
 	   doPost(req, resp);
    }
}
