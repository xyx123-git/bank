

package com.cx.bank.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cx.bank.manager.ManagerImpl;
import com.cx.bank.model.UserBean;
import com.cx.bank.util.InvalidDepositException;


public class BeforeFreezeServlet extends HttpServlet{
	 public void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
		 req.setCharacterEncoding("UTF-8");
		 ManagerImpl mi=ManagerImpl.getInstance();
		 List list=(List) mi.findAllUser();
		 req.setAttribute("names",list);
		 req.getRequestDispatcher("/WEB-INF/Freeze.jsp").forward(req, resp);
     }
     public void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
  	   doPost(req, resp);
     }
}
