package com.test.sku.inheritance;

import java.util.Date;

public class Monitor extends Item {
	
	float size;

	public Monitor() {}
	
	@Override
	public String toString() {
		return super.toString()+"\t"+size;
	}
	
	public Monitor(String name, String made, int price, Date pDate, float size) {
		super(name, made, price, pDate);
		setSize(size);
	}

	public Monitor(String name, String made, int price, String sDate, float size) {
		super(name, made, price, sDate);
		setSize(size);
	}
	
	public float getSize() {
		return size;
	}

	public void setSize(float size) {
		this.size = size;
	}
	
}
