package com.cx.bank.util;
/**
 * 
 * @author xyx
 *存款为负数时抛出异常
 */
public class InvalidDepositException extends Exception {
 
public InvalidDepositException() {
	   
   }
   public InvalidDepositException(String msg) {
	   super(msg);
	  
   }
}
