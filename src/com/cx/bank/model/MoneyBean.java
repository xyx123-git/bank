package com.cx.bank.model;

public class MoneyBean {
   // private double money;
    public double money;
    private static MoneyBean instance1;
    private MoneyBean() {
    	//this.money=themoney;
    }
    public static MoneyBean getInstance1() {
    	if(instance1==null) {
 		   instance1=new MoneyBean();}
    	return instance1;
    }
    public void setMoney(double money) {
    	this.money=money;
    }
    public double getMoney() {
    	return this.money;
    }
}
