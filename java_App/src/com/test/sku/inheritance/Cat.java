package com.test.sku.inheritance;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Cat extends Pet {
	String pattern; 
	float age;
	
	public Cat() {}
	
	@Override
	public String toString() {
		return super.toString() +"\t"+ pattern +"\t\t\t"+ age;
	}
	
	public Cat(int price, String species, String pattern, float age) {
		super(price, species);
		setPattern(pattern);
		setAge(age);
	}
	
	public Cat(String[] token) { 
		super(Integer.parseInt(token[0]), token[1]);
        String pattern = token[2];
        float age = Float.parseFloat(token[3]);  
        setPattern(pattern);
        setAge(age);
	}
	
	public String getPattern() {
		return pattern;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	public float getAge() {
		return age;
	}
	public void setAge(float age) {
		this.age = age;
	}
	
	
}
