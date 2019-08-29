package com.cx.bank.dao;
/**
 * 持久层

 */
import java.io.File;





import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JOptionPane;

import org.omg.CORBA.PRIVATE_MEMBER;

import com.cx.bank.model.LogBean;
import com.cx.bank.model.MoneyBean;
import com.cx.bank.model.UserBean;
import com.cx.bank.util.JDBCUtils;
import com.cx.bank.util.MD5;




public class BankDaoImpl implements BankDaoInterface{
	
	//public static double money;
	//private static BankDaoImpl instance;
    public BankDaoImpl() {
    	
    }
    /**
    public static synchronized BankDaoImpl getInstance() {//拿到对象的地址
 	   if(instance==null) {
 		   instance=new BankDaoImpl();
 	   }
 	   return instance;//BankDaoImpl类型对象的地址
    }
    */
    
    /*
     * 存款、取款操作结束后，需要把该用户操作后的余额存入到数据库
     * @see com.cx.bank.dao.BankDaoInterface#AddBank(java.lang.String, double)
     */
    
    public Double AddBank(String name,double usermoney) {
    	/*
    	Properties props=new Properties();
    	String money=String.valueOf(usermoney);
    	try {
    	InputStream fis=new FileInputStream("C:\\Users\\xyx\\eclipse-workspace\\Bank\\"+name+".properties");
		props.load(fis);
		fis.close();
		props.setProperty("money",money);
		 FileOutputStream fos=new FileOutputStream(name+".properties");
         props.store(fos, name+".properties");
         fos.close();
    	}catch(IOException e) {
    		System.out.println("找不到文件！！");
    	}
		*/
    	//double money1=moneybean.getMoney();
    	//String name=userbean.getName();
    	//double current = 0;
    	Connection conn=null;
    	Statement stmt=null;
		PreparedStatement stmt1=null;
		ResultSet rs=null;
		int num=0;
		int flag = 0;
		try {
			
			conn=JDBCUtils.dbOpen();
			//String mysql="SELECT * FROM `t_user`";
			//stmt=conn.createStatement();
			//rs=stmt.executeQuery(mysql);
			//while(rs.next()) {
				
				//rs.getInt("id");
				//if(name.equals(rs.getString("user_name"))) {//查找与该用户名对应的user_flag字段的值
					//flag=rs.getInt("user_flag");
				//}
			//}
					//System.out.println("该用户不存在");//如果遍历完整张表都没有找到，则认为该用户不存在
			
			//if(0==flag) {//如果该用户的flag为0这说明该用户的账号没有被冻结
			String sql="update t_user set balance=? where user_name=?";//更新某一用户的余额字段的值
			stmt1 = conn.prepareStatement(sql);
			//stmt1.setString(1,String.valueOf(money1));
			stmt1.setDouble(1,usermoney);//将存款或取款后的用户余额重新设到用户对应的字段中
			stmt1.setString(2,name);
			//stmt1.setString(2,name);
			     stmt1.execute();  
			//System.out.println("存款成功");
			return usermoney;
			//}
    	}catch(Exception e) {
    		e.getStackTrace();
    		//System.out.println("存款失败");

    	}finally {
    		try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return usermoney;
    }
    
    
    /**
     * 注册功能
     */
    public boolean register(UserBean userbean) {
    	String name=userbean.getName();
    	String pwd=userbean.getPassword();
    	Connection conn=null;
		PreparedStatement stmt=null;
    	String upass=MD5.encode(pwd.getBytes());
    	int num=0;
    	try {
			conn=JDBCUtils.dbOpen();
			String sql="insert into `t_user`(`user_name`,`user_password`,`user_flag`,`balance`)" + 
	    			"values(?,?,?,?)";
			//Class.forName("com.mysql.jdbc.Driver");
			
			stmt = conn.prepareStatement(sql);
			//stmt.setInt(1,2);
			stmt.setString(1,name);
			stmt.setString(2,upass);
			stmt.setInt(3,0);
			stmt.setDouble(4,200);
			num = stmt.executeUpdate();
		    
		   System.out.println("注册成功！");
           return true;
    	}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("读取文件错误");
            return false;
		}finally {
			
			JDBCUtils.dbClose(conn, stmt);
		}
    	/**
    	File f=new File(name+".properties");
    	if(f.exists()) {
    		System.out.println("该用户已存在，请重新注册");
    		return false;}
    	else {
    			try {
    				Properties props=new Properties();
    				FileInputStream fis=new FileInputStream(new File("Bank.properties"));
    				props.load(fis);
                    fis.close();
                    String upass=MD5.encode(pwd.getBytes());//利用MD5技术s对用户密码进行加密
                    props.setProperty("username",name);
                    props.setProperty("password",upass);
                    props.setProperty("money","200");
                    FileOutputStream fos=new FileOutputStream(name+".properties");
                    props.store(fos, name+".properties");
                    fos.close();
                    System.out.println("注册成功！");
                    return true;
    			}catch(IOException e) {
    				System.out.println("读取文件错误");
                    return false;
    			}
    		
    	}
    	*/
    }
    
    /**
     * 登录功能
     * 
     */
    
    public boolean login(UserBean userbean){
    	String name=userbean.getName();
    	String password=userbean.getPassword();
    	String passw1=MD5.encode(password.getBytes());
    	Connection conn = null;
    	Statement stmt=null;
    	ResultSet rs=null;
		try {
			conn = JDBCUtils.dbOpen();
			String sql="SELECT * FROM `t_user`";
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()) {
				if(name.equals(rs.getString("user_name"))) {
					if(passw1.equals(rs.getString("user_password"))) {
						double money=rs.getDouble("balance");//如果用户登录成功后拿到用户的余额
						return true;
					}else {
						System.out.println("密码错误");
						
					}
				}
			}
				System.out.println("用户不存在，请注册！！！");	
    	}catch(Exception e) {
    		e.getStackTrace();
    	
    	}finally {
    		JDBCUtils.dbClose(conn,stmt,rs);
    	
		}
				return false;
    }
    	/*
		File f=new File("C:\\Users\\xyx\\eclipse-workspace\\Bank\\"+name+".properties");//创建文件对象用于存放要查找对象的propertiy文件的地址
		Properties props=new Properties();
		try {
    	if(f.exists()) {//如果有与登录用户的用户名相同的文件就读入该文件
    		
        	InputStream fis=new FileInputStream("C:\\Users\\xyx\\eclipse-workspace\\Bank\\"+name+".properties");
    		props.load(fis);
    		fis.close();//关闭输入流对象
    		String passw1=MD5.encode(password.getBytes());//用MD5技术对密码进行加密
    		String passw2=props.getProperty("password");//拿到该用户文件里已经加密过的秘文
    		
    		
    		//Properties props=new Properties();
    	
            //FileOutputStream fos=new FileOutputStream(name+".properties");
            //fos.close();
          
    		if(passw2.equals(passw1)) {//用户登录的密码与文件中的密码相同则说明该用户已经注册成功
    			
    			MoneyBean moneybean=MoneyBean.getInstance1();//使用单例拿到moneybean对象的地址
    			double usermoney=Double.parseDouble(props.getProperty("money"));//把文件中String类型的money属性转化为MoneyBean中对money属性要求的类型double
    			moneybean.setMoney(usermoney);//把初始化的余额存入moneybean对象中，以便完成后续对money属性的操作
    			System.out.println("登录成功！");
    			return true;
    		}else {
    			System.out.println("密码错误！");
    			return false;
    		}
    		
    	}else {
			System.out.println("用户不存在！请先注册！");
			return false;
		} 
		}catch(IOException e) {
			System.out.println("文件不存在！");
			//e.printStackTrace();
			return false;
		}
		*/
		
    	
    
    /**
     * 转账功能
     * 
     */
    public boolean transfer(String username,String toname,double money,double usermoney) {
/*
    	File f=new File("C:\\Users\\xyx\\eclipse-workspace\\Bank\\"+username+".properties");//创建文件对象用于存放要查找对象的propertiy文件的地址
		Properties props=new Properties();
		try {
		InputStream fis=new FileInputStream("C:\\Users\\xyx\\eclipse-workspace\\Bank\\"+username+".properties");
		props.load(fis);
		fis.close();
		
		}catch(IOException e) {
			System.out.println("读取用户文件错误！");
			e.printStackTrace();
			return false;
		}
		//String passw2=props.getProperty("password");
		MoneyBean moneybean=MoneyBean.getInstance1();//使用单例拿到moneybean对象的地址
		double usermoney=Double.parseDouble(props.getProperty("money"));//拿到文件里的money的值
		moneybean.setMoney(usermoney);//把文件里的money的值设到该用户对应的moneybean中
		File f1=new File("C:\\Users\\xyx\\eclipse-workspace\\Bank\\"+toname+".properties");//创建文件对象用于存放要查找对象的propertiy文件的地址
		Properties props1=new Properties();
		try {
		InputStream fis1=new FileInputStream("C:\\Users\\xyx\\eclipse-workspace\\Bank\\"+toname+".properties");
		props1.load(fis1);
		fis1.close();}catch(IOException e1) {
			System.out.println("读取收款人文件错误！");
			return false;
		}
		if(usermoney<money) {
			System.out.println("余额不足！请先充值");
			return false;
		}else {
			usermoney-=money;//转账人的money的值减去要转账的金额
			String money1=String.valueOf(usermoney);
			props.setProperty("money", money1);//把转账后的余额重新设到文件的money属性中
			try {
			FileOutputStream fos=new FileOutputStream(username+".properties");
            props.store(fos, username+".properties");
            fos.close();
            System.out.println("您的余额为："+usermoney);
            double usermoney1=Double.parseDouble(props1.getProperty("money"));
            usermoney1+=money;
            String money2=String.valueOf(usermoney1);
            props1.setProperty("money", money2);
            FileOutputStream fos1=new FileOutputStream(toname+".properties");
            props1.store(fos1, toname+".properties");
            fos1.close();}catch(IOException e2) {
            	System.out.println("关闭文件错误！");
            	return false;
            }
            return true;
		}
		*/
    	Connection conn=null;
    	double tonameMoney=0;
		PreparedStatement stmt1=null;
		Statement stmt=null;
    	ResultSet rs=null;
		int num=0;
		int toid = 0;//接受者的id
		int toflag=1;//接受者的flag字段的值
		int flag=1;//转账人的flag字段的值
		try {
			conn=JDBCUtils.dbOpen();
			String mysql="SELECT * FROM `t_user`";
			stmt=conn.createStatement();
			rs=stmt.executeQuery(mysql);
			while(rs.next()) {
				if(toname.equals(rs.getString("user_name"))) {
					tonameMoney=rs.getDouble("balance");//拿到接收者的余额
					toid=rs.getInt("user_id");//拿到接收者的id
					toflag=rs.getInt("user_flag");//确定接受者的账号是否被冻结
				}
			}
			//while(rs.next()) {
				//if(username.equals(rs.getString("user_name"))) {
					//flag=rs.getInt("user_flag");//拿到转账人的flag字段的值，用于判断该用户是否被冻结，0是没有被冻结，1为账号被冻结
				//}
			//}
				//if(flag==0) {
					if(toflag==0) {
			String sql="update t_user set balance=? where user_name=?";
			stmt1 = conn.prepareStatement(sql);
			//stmt1.setString(1,String.valueOf(money1));
			stmt1.setDouble(1,money+tonameMoney);//如果转账人和接受人的账户都没有被冻结，就把转账人的余额减去转账金额，接收人的余额加上转账金额
			stmt1.setString(2,toname);
		    stmt1.execute();
		    stmt1.setDouble(1,usermoney);
			stmt1.setString(2,username);
			stmt1.execute();  
			String usersql="insert into `t_log`(`log_type`,`log_amount`,`userid`)"+"values(?,?,?)";
			stmt1 = conn.prepareStatement(usersql);
			stmt1.setString(1,"转入");
			stmt1.setDouble(2,money);
			stmt1.setInt(3,toid);
			stmt1.execute(); 
			return true;
					}
				//}
				
    	}catch(Exception e) {
    		e.getStackTrace();

    	}finally {
    		try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
    }
    
    /*
     * 查询当前用户的余额，连接数据库拿到与用户名对应的对象的余额
     * @see com.cx.bank.dao.BankDaoInterface#inquiry(com.cx.bank.model.UserBean)
     */
   
    public Double inquiry(UserBean userbean) {
    	
    	String name=userbean.getName();
    	//double current = 0;
    	Connection conn=null;
		
    	Statement stmt=null;
    	ResultSet rs=null;
		try {
			conn = JDBCUtils.dbOpen();
			String sql="SELECT * FROM `t_user`";
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()) {
				
				//rs.getInt("id");
				if((rs.getString("user_name")).equals(name)) {
					
						double money=rs.getDouble("balance");
						return money;
				}
			}
			System.out.println("用户不存在，请注册***");
    	}catch(Exception e) {
    		e.getStackTrace();
    	
    	}finally {
    		JDBCUtils.dbClose(conn,stmt,rs);
		}
		return null;
    }
    
    /*
     * 把用户的操作添加到用户明细表中
     * @see com.cx.bank.dao.BankDaoInterface#AddLog(java.lang.String, double, java.lang.String)
     */
    public void AddLog(String username,double money,String type) {
        Connection conn=null;
		
    	Statement stmt=null;
    	ResultSet rs=null;
    	int id = 0;
        int flag=0;
		try {
			conn = JDBCUtils.dbOpen();
			String sql="SELECT * FROM `t_user`";
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()) {
				
				//rs.getInt("id");
				if(username.equals(rs.getString("user_name"))) {
					
						id=rs.getInt("user_id");
						flag=rs.getInt("user_flag");
						
				}
							//return false;
				
			}
			if("null".equals("id")) {
			System.out.println("用户不存在，请注册");}
			if(0==flag) {
			    PreparedStatement stmt1=null;
	    	    int num=0;
				String mysql="insert into `t_log`(`log_type`,`log_amount`,`userid`)" + 
		    			"values(?,?,?)";
				//Class.forName("com.mysql.jdbc.Driver");
				
				stmt1 = conn.prepareStatement(mysql);
				//stmt.setInt(1,2);
				stmt1.setString(1,type);
				stmt1.setDouble(2,money);
				stmt1.setInt(3,id);
				num = stmt1.executeUpdate();
			}
				
    	}catch(Exception e) {
    		e.getStackTrace();
    	
    	}finally {
    		try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    }
    
    
    /*
     * 查找当前用户的所有操作明细
     * @see com.cx.bank.dao.BankDaoInterface#findAll(com.cx.bank.model.UserBean)
     */
    public List findAll(UserBean userbean) throws SQLException {
		
		Connection conn=JDBCUtils.dbOpen();
		Statement stmt1=null;
		ResultSet rs=null;
		Statement stmt=null;
		String username=userbean.getName();
    	int id = 0;
		try {
			conn=JDBCUtils.dbOpen();
			String sql="SELECT * FROM `t_user`";
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()) {
				
				//rs.getInt("id");
				if(username.equals(rs.getString("user_name"))) {
					
						id=rs.getInt("user_id");//拿到用户对应的id
						
				}
					
					//return false;
			}
			System.out.println("用户不存在，请注册");
			
			String mysql="SELECT * FROM `t_log`";
			stmt1=conn.createStatement();
			rs=stmt1.executeQuery(mysql);
		    List data=new ArrayList<>();//创建一个vector集合存放从数据库中的拿到的数据
			while(rs.next()) {
				if(id==(rs.getInt("userid"))) {//拿到与用户对应的每个字段的值，再设到集合中
				LogBean lBean=new LogBean();
				lBean.setId(rs.getInt("log_id"));
				lBean.setType(rs.getString("log_type"));
				lBean.setMoney(rs.getDouble("log_amount"));
				lBean.setName(username);
				data.add(lBean);//把每一条记录作为新集合中的一个元素
				}
			}
			System.out.println("该用户还没有进行操作");
			return data;//将集合返回
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.dbClose(conn,stmt1,rs);
		}
	return null;
}
 
    
    /*
     * 冻结指定账户，令其不能再进行存款取款转账操作，只能查询
     */
    public boolean freeze(String name) {
    	Connection conn=null;
		PreparedStatement stmt1=null;
		//int num=0;
		try {
			conn=JDBCUtils.dbOpen();
			String sql="update t_user set user_flag=? where user_name=?";
			
			stmt1 = conn.prepareStatement(sql);
			//stmt1.setString(1,String.valueOf(money1));
			stmt1.setInt(1,1);
			stmt1.setString(2,name);
			stmt1.execute();  
			return true;
    	}catch(Exception e) {
    		e.getStackTrace();
    		return false;
    	}finally {
    		try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
    }
    
    /*
     *查询当前用户是否被冻结，在该用户登录后进行存取款以及转账之前先调用此方法查询数据库，如果flag为1就说明该用户已被冻结不能进行操作
     * @see com.cx.bank.dao.BankDaoInterface#checkflag(java.lang.String)
     */
    public int checkflag(String name) {
    	ResultSet rs=null;
		Statement stmt=null;
		Connection conn=null;
    	int flag = 1;
		try {
			conn=JDBCUtils.dbOpen();
			String sql="SELECT * FROM `t_user`";
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()) {
				
				//rs.getInt("id");
				if(name.equals(rs.getString("user_name"))) {
					
						flag=rs.getInt("user_flag");//拿到用户对应的id
						//System.out.print(flag);
						return flag;
						
				}
					
					//return false;
			}
		}catch(Exception e) {
    		e.getStackTrace();
    	}finally {
    		try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
		
    }
    
    
    public void warm(String username) {
    	Connection conn=null;
		PreparedStatement stmt1=null;
		//int num=0;
		try {
			conn=JDBCUtils.dbOpen();
			String sql="update t_user set user_flag=? where user_name=?";
			
			stmt1 = conn.prepareStatement(sql);
			//stmt1.setString(1,String.valueOf(money1));
			stmt1.setInt(1,0);
			stmt1.setString(2,username);
			stmt1.execute();  
    	}catch(Exception e) {
    		e.getStackTrace();
    		
    	}finally {
    		try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
    }
    
    
    
    public List findAllUser() {
    	ResultSet rs=null;
		Statement stmt=null;
		Connection conn=null;
    	List list=new ArrayList();
		try {
			conn=JDBCUtils.dbOpen();
			String sql="SELECT * FROM `t_user`";
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()) {
		     UserBean ub=new UserBean();
			 ub.setName(rs.getString("user_name"));
			 ub.setUserid(rs.getInt("user_id"));
			 ub.setAmount(rs.getDouble("balance"));
			 ub.setFlag(rs.getInt("user_flag"));
			 list.add(ub);
			}
			return list;
		}catch(Exception e) {
    		e.getStackTrace();
    	}finally {
    		try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
    }


    
    public List findAllFreezer() {
    	ResultSet rs=null;
		Statement stmt=null;
		Connection conn=null;
    	List list=new ArrayList();
		try {
			conn=JDBCUtils.dbOpen();
			String sql="select * from `t_user` where user_flag='1'";
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()) {
		     UserBean ub=new UserBean();
			 ub.setName(rs.getString("user_name"));
			 ub.setUserid(rs.getInt("user_id"));
			 list.add(ub);
			}
			return list;
		}catch(Exception e) {
    		e.getStackTrace();
    	}finally {
    		try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
    }
    
    
    public List findAllTag() {
    	Connection conn=null;
    	List list=new ArrayList<>();
    	Statement stmt=null;
    	ResultSet rs=null;
		try {
			conn = JDBCUtils.dbOpen();
			String sql="SELECT * FROM `t_log`";
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
				LogBean lb=new LogBean();
				lb.setId(rs.getInt("log_id"));
				lb.setMoney(rs.getDouble("log_amount"));
				lb.setUserid(rs.getInt("userid"));
				lb.setType(rs.getString("log_type"));
				list.add(lb);
			}
			return list;
    	}catch(Exception e) {
    		e.getStackTrace();
    	
    	}finally {
    		JDBCUtils.dbClose(conn,stmt,rs);
		}
		return list;
    }
}

