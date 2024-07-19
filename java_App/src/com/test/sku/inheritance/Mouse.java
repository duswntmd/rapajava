package com.test.sku.inheritance;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Mouse extends Item {
	
	String wireless;

	public Mouse() {}
	
	@Override
	public String toString() {
		return super.toString()+"\t"+wireless;
	}

	public Mouse(String name, String made, int price, Date pDate, String wireless) {
		super(name, made, price, pDate);
		setWireless(wireless);
	}

	public Mouse(String name, String made, int price, String sDate, String wireless) {
		super(name, made, price, sDate);
		setWireless(wireless);
	}

	public String getWireless() {
		return wireless;
	}

	public void setWireless(String wireless) {
		this.wireless = wireless;
	}
	
	
}
