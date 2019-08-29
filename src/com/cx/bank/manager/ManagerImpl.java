package com.cx.bank.manager;
/**
 * 业务层
 */

import java.awt.event.MouseAdapter;



import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JSpinner.ListEditor;

import com.cx.bank.dao.BankDaoImpl;

import com.cx.bank.dao.BankDaoInterface;
import com.cx.bank.factory.UserDaoFactory;
import com.cx.bank.model.MoneyBean;
import com.cx.bank.model.UserBean;
import com.cx.bank.util.AccountOverDrawnException;
import com.cx.bank.util.InvalidDepositException;


public class ManagerImpl extends MouseAdapter implements ManagerInterface {
	//public double money;//实例全局变量
	//private static ManagerImpl instance=new ManagerImpl(20);//创建一个静态全局变量指向对象的地址
	private static ManagerImpl instance;//默认是null
	//MoneyBean moneybean=new MoneyBean();//创建MoneyBean类型的对象，从而使用对象里的方法
	 MoneyBean moneybean=MoneyBean.getInstance1();
	 //private double money=moneybean.getMoney();
	 //BankDaoInterface bankdao=BankDaoImpl.getInstance();//持久层对象必须通过接口拿
	private BankDaoInterface bankdao;
   private ManagerImpl()throws Exception {//私有构造方法，使得不能new对象，实现单例
	  //moneybean.setMoney(themoney);//把形参的值设到对象的属性中
	  //this.money=moneybean.getMoney();
	   UserDaoFactory userdaofactory=UserDaoFactory.getInstance();
	   bankdao=userdaofactory.createUserDao();
	   
   }
   public static synchronized ManagerImpl getInstance(){//拿到对象的地址
	   if(instance==null) {
		   try {
			instance=new ManagerImpl();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   }
	   return instance;//ManagerImpl类型对象的地址
   }
   
   public boolean register(String name,String pwd) {
	   UserBean userbean=new UserBean();
	   if("".equals(name)||"".equals(pwd)) {
			System.out.println("用户名和密码不能为空");
			return false;}
		else {
	   userbean.setName(name);
	   userbean.setPassword(pwd);
	   boolean flag=bankdao.register(userbean);
	   return flag;
   }
   }
   
   public boolean login(String name,String password) {
	   UserBean userbean=new UserBean();
	   if("".equals(name)||"".equals(password)) {
			System.out.println("用户名和密码不能为空");
			return false;}
	   else {
		   userbean.setName(name);
		   userbean.setPassword(password);
		   boolean flag=bankdao.login(userbean);
		   if(true==flag) {
		   double money=bankdao.inquiry(userbean);
		   moneybean.setMoney(money);
		   }
		   return flag;
	   }
   }
   
   
   
   
   /**
    * 像该账户存钱
    * @param save
    * @return
 * @throws InvalidDepositException 
    */
   
   public double deposit(UserBean userbean,String thesave) throws InvalidDepositException {//不处理自定义的异常类，即不处理输入的存款金额<0
	//moneybean.setMoney(20);
	//this.money=moneybean.getMoney(); 
	   double current;
	   double save=Double.parseDouble(thesave);
	   String username=userbean.getName();
	   if(save<=0)  throw new InvalidDepositException("存款金额需大于0，请重新输入存款金额：");//当存款金额小于等于0时系统将自定义的异常类对象抛给运行时，到测试类中找处理异常的代码
		  // System.out.println("存款金额需大于0");
	   else {
		   moneybean.money+=save;
		   moneybean.setMoney(moneybean.money);
		   current=bankdao.AddBank(username,moneybean.getMoney());
		   bankdao.AddLog(userbean.getName(),save,"存款");
		   //bankdao.deposit(userbean,moneybean);
	   System.out.println("您的存款为："+moneybean.money);
		  // System.out.println("您的存款为："+current);
	   }
	   return current;
	  // return ;
   }
   /**
    * 从该账户中取钱
    * @param leave
    * @return
 * @throws AccountOverDrawnException 
    */
   public double withdrawals(UserBean userbean,String theleave) throws AccountOverDrawnException,InvalidDepositException {//本方法声明不处理输入为负数和取款金额大于已有存款的异常
	   //this.money=moneybean.getMoney();
	   String username=userbean.getName();
	   double leave=Double.parseDouble(theleave);
	   double k;
	   if(leave<=0) throw new InvalidDepositException("存款金额需大于0");//当取款金额小于等于0时系统将自定义的异常类对象抛给运行时，到测试类中找处理异常的代码
	   if(leave>moneybean.money) {//当取款金额大于存款金额时，把异常类对象抛给运行时去测试类中找处理异常的代码
		   //System.out.println("余额不足");
	   return moneybean.money-leave;}
	   else {
		   moneybean.money-=leave;
		   moneybean.setMoney(moneybean.money);
		   //System.out.println(this.money);
		   k=bankdao.AddBank(username,moneybean.money);
		   bankdao.AddLog(userbean.getName(),leave,"取款");
		   System.out.println("您的余额为："+moneybean.money);
	   }
		   return k;
   }
   /**
    * 查询该账户中的余额
    */
   public double inquiry(UserBean userbean) {
	   //this.money=moneybean.getMoney();
	   System.out.println(moneybean.getMoney());
	   System.out.println("您的余额为："+moneybean.getMoney());
	   double money=bankdao.inquiry(userbean);
	   return money;
   }
   /**
    * 退出操作
    */
   public void exitSystem(String username) {
	  bankdao.AddBank(username,moneybean.getMoney());
	   System.exit(0);
   }
   /**
    * 转账操作
    */
   public boolean transfer(String name,String towhom,String howmuch) {
	   double howmuch1=Double.parseDouble(howmuch);
	   double  howmuch2=moneybean.getMoney()-howmuch1;
	   moneybean.setMoney(howmuch2);
	boolean k=bankdao.transfer(name,towhom,howmuch1,howmuch2);
	System.out .println(k);
	   if(k) {
		   System.out.print("转账成功");
		   bankdao.AddLog(name,howmuch1,"转出");
		   return true;
	   }else {
	   return false;
	   }
   }
   
   /**
    * 查询明细操作
    */
   public List findAll(UserBean userbean) {
	 List data=new ArrayList<>();
	try {
		data = bankdao.findAll(userbean);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   return data;
   }
   /*
    * 冻结用户账号
    */
   public boolean freeze(String name) {
	   boolean k=bankdao.freeze(name);
	   return k;
   }
   
   /*
    * 查询当前用户是否被冻结
    * 
    */
  public int checklog(UserBean userbean) {
	  String username=userbean.getName();
	  int flag=bankdao.checkflag(username);
	  return flag;
  }
  
  
  public int warm(String user) {
	  bankdao.warm(user);
	 // UserBean userbean=new UserBean();
	  //userbean.setName(user);
	  int flag=bankdao.checkflag(user);
	  return flag;
  }
  
  
  public List findAllUser() {
	  List list=(List) bankdao.findAllUser();
	  return list;
  }
  
  public List findAllFreezer() {
	  List list=(List) bankdao.findAllFreezer ();
	  return list;
  }
  
  public List findAllTag() {
	 List list= bankdao.findAllTag();
	 return list;
	  
  }
}
