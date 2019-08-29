package com.cx.bank.factory;
/**
 * 工厂模式，利用反射加工厂实现自动装配
 */
import java.io.File;


import java.io.FileInputStream;
import java.util.Properties;

import com.cx.bank.dao.BankDaoImpl;
import com.cx.bank.dao.BankDaoInterface;



public class UserDaoFactory {
    private static UserDaoFactory instance;
    private BankDaoInterface userdao;
    
    @SuppressWarnings("rawtypes")
	private UserDaoFactory() throws Exception{
    	Properties store=new Properties();//创建文件对象
        FileInputStream fis=new FileInputStream(new File("C:\\Users\\xyx\\eclipse-workspace\\Bank2.0\\WebContent\\classInfo.properties"));//加载指定文件
    	store.load(fis);
    	fis.close();//关闭流对象
    	
    	String p=store.getProperty("className");//拿到property文件的classname属性值
    	Class c=Class.forName(p);//根据类全名获得class对象，创建反射对象
    	Object o=c.newInstance();//创建持久层对象，实例化该对象，注意业务层和持久层的构造方法要改为public的，否则无法实现自动装配
    	
    	userdao=(BankDaoInterface)o;//造型
    	
    }
    
    public static synchronized UserDaoFactory getInstance()throws Exception {
    	if(instance==null) {
    		instance=new UserDaoFactory();
    	}
    	return instance;
    }
  public BankDaoInterface createUserDao() {
    	return userdao;
 }
   
}
