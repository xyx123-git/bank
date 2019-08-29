package com.cx.bank.manager;
import java.awt.event.MouseListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.cx.bank.model.UserBean;
/**
 * 业务层接口，实现对业务层方法的功能规范
 */
import com.cx.bank.util.AccountOverDrawnException;
import com.cx.bank.util.InvalidDepositException;


public interface ManagerInterface {
    
    public abstract double deposit(UserBean userbean,String save) throws InvalidDepositException;
    public abstract double withdrawals(UserBean userbean,String leave) throws AccountOverDrawnException, InvalidDepositException;
    public double inquiry(UserBean userbean);
    public abstract void exitSystem(String username);
    public abstract boolean register(String name,String pwd);
    public abstract boolean login(String name,String password);
    public abstract boolean transfer(String name,String towhom,String howmuch);
    
    public List findAll(UserBean userbean) ;
    public boolean freeze(String name);
    public int checklog(UserBean userbean);
    public int warm(String user) ;
    public List findAllUser();
    public List findAllFreezer();
    public List findAllTag();
}
