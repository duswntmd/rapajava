package com.test.sku.stream;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.test.sku.list.Employee;

public class Member {
	private int no;
	private String name;
	private String phone;
	private String email;
	
	public Member() {}
//	public Member(int no) 
//	{
//		this.no = no;
//	}
	
	public Member(int no)
	{
		setNo(no);
	}
	
	public Member(int no, String name, String phone, String email)
	{	
		setNo(no);
		setName(name);
		setPhone(phone);
		setEmail(email);
	}
	
	public Member(String line) 
	{
		String[] token = line.split("\\|");
		setNo(Integer.parseInt(token[0]));
		setName(token[1]);
		setPhone(token[2]);
		setEmail(token[3]);
	}
	
	@Override
	public boolean equals(Object obj) {
		Member other = (Member) obj;
		return this.getNo()==other.getNo();
	}
	@Override
	public String toString() {
		return String.format("%d\t%20s\t%s\t%s", no,name,phone,email);
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
