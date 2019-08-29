/*
 * xyx 2019.8.13
 * 管理员操作界面，查看所有用户的操作明细
 *返回的是list集合
 */

package com.cx.bank.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cx.bank.manager.ManagerImpl;

public class FindAllTagServlet extends HttpServlet{
	public void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");//编码
		ManagerImpl mi=ManagerImpl.getInstance();//通过单例拿到业务层对象
		 List list=(List) mi.findAllTag();//调用业务层的方法
		 req.setAttribute("tags",list);//把查询返回的list集合放在request中传给前台页面显示
		 req.getRequestDispatcher("showAllTags.jsp").forward(req, resp);
   }
   public void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
	   doPost(req, resp);
   }
}
