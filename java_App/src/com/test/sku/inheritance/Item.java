package com.test.sku.inheritance;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Item extends Object {
	
	String name;
	String made;
	int price;
	Date pDate;
	
	public Item() {}
	
	public Item(String name, String made, int price, Date pDate) {
		super();
		this.name = name;
		this.made = made;
		this.price = price;
		this.pDate = pDate;
	}
	
	public Item(String name, String made, int price, String sDate) {
		this.name = name;
		this.made = made;
		this.price = price;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date pDate = null;
		try {
			pDate = sdf.parse(sDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.pDate = pDate;
	}

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String sDate = sdf.format(pDate);
		return String.format("%s\t%s\t%d\t%s", name, made, price, sDate);
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMade() {
		return made;
	}

	public void setMade(String made) {
		this.made = made;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Date getpDate() {
		return pDate;
	}

	public void setpDate(Date pDate) {
		this.pDate = pDate;
	}
	
	public void setpDate(String sDate) { // setpDate() 오버로드
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date pDate = null;
		try {
			pDate = sdf.parse(sDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.pDate = pDate;
	}
}
