package com.cx.bank.dao;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.cx.bank.model.MoneyBean;
/**
 * 持久层接口
 */
import com.cx.bank.model.UserBean;

public interface BankDaoInterface {
	
  public abstract boolean register(UserBean userbean);
  public abstract boolean login(UserBean userbean);
  public abstract Double AddBank(String name,double money) ;
  public abstract boolean transfer(String username,String toname,double money,double usermoney);
  public abstract  Double inquiry(UserBean userbean) ;
  public abstract void AddLog(String username,double money,String type) ;
  public List findAll(UserBean userbean) throws SQLException;
  public boolean freeze(String name);
  public int checkflag(String name) ;
  public void warm(String username);
  public List findAllUser();
  public List findAllFreezer();
  public List findAllTag();
 
}
