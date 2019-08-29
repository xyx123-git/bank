package com.cx.bank.model;

import org.apache.struts.action.ActionForm;

public class UserBean extends ActionForm{
    private String name;
    private String password;
    private int logid;
    private String type;
    private double amount;
    private int userid;
    private int flag;
    private String deposit;
    private String leave;
    private String towhom;
    private String money;
 
 public String getDeposit() {
 	return deposit;
 }
 public void setDeposit(String deposit) {
 	this.deposit = deposit;
 }
 public String getLeave() {
 	return leave;
 }
 public void setLeave(String leave) {
 	this.leave = leave;
 }
 public String getTowhom() {
 	return towhom;
 }
 public void setTowhom(String towhom) {
 	this.towhom = towhom;
 }
 public String getMoney() {
 	return money;
 }
 public void setMoney(String money) {
 	this.money = money;
 }
    public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public UserBean() {	
    }
    public  void setName(String name) {
    	this.name=name;
    }
    public String getName() {
    	return this.name;
    }
    public void setPassword(String password) {
    	this.password=password;
    }
    public String getPassword() {
    	return this.password;
    }
    public void setLogid(int logid) {
    	this.logid=logid;
    }
    public int getLogid() {
    	return this.logid;
    }
    public void setType(String type) {
    	this.type=type;
    }
    public String getType() {
    	return this.type;
    }
    public void setAmount(Double amount) {
    	this.amount=amount;
    }
    public double getAmount() {
    	return this.amount;
    }
    public void setUserid(int userid) {
    	this.userid=userid;
    }
    public int getUserid() {
    	return this.userid;
    }
}
