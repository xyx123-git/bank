package com.cx.bank.util;
/**
 * 
 * @author xyx
 *取款超出余额时抛出异常
 */
public class AccountOverDrawnException extends Exception{
    public AccountOverDrawnException() {
    	
    }
    public AccountOverDrawnException(String msg) {
    	super(msg);
    }
}
