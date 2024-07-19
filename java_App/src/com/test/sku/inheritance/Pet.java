package com.test.sku.inheritance;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Pet {
	
	int price;
	String species;
	
	
	public Pet() {}
	
	public Pet(int price,String species) {
		super();
		this.price = price;
		this.species = species;
	}
	
	@Override
	public String toString() {
		return String.format("%d\t%s", price, species);
	}
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getSpecies() {
		return species;
	}
	public void setSpecies(String species) {
		this.species = species;
	}
	
	
}
