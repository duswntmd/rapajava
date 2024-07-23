package com.test.sku.network;

import java.io.Serializable;

public class ChatMsg implements Serializable{
	
	String uid;
	String pwd;
	String from;
	String to;
	String msg;
	boolean login;
	byte[] fdata;

	public ChatMsg() {}
	
	public ChatMsg(boolean login, String uid, String pwd) {
		this.login = login;
		this.uid = uid;
		this.pwd = pwd;
	}
	
	public ChatMsg(String from, String to, String msg) {
		this.from = from;
		this.to = to;
		this.msg = msg;
	}
	
	
	
}
