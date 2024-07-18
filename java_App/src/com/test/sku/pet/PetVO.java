package com.test.sku.pet;

import java.io.Serializable;
import java.text.NumberFormat;

public class PetVO implements Comparable<PetVO>, Serializable {//직열화해도됨
	
	//Pet(no, species(품종), weight, price)
	private int no;
	private String species;
	private float weight;
	private int price;
	
	public PetVO() {}
	
	public PetVO(int no) {
		setNo(no);
	}
	
	@Override
	public int compareTo(PetVO other) {
		// 왼쪽의 객체가 크면 양수, 동일하면 0, 작으면 음수
		if(this.getNo()>other.getNo()) return 1;
		else if(this.getNo()==other.getNo()) return 0;
		else return -1;
	}
	
	@Override
	public boolean equals(Object obj) {
		PetVO other = (PetVO)obj;
		return this.getNo() == other.getNo();
	}
	@Override
	public String toString() {
	    NumberFormat nf = NumberFormat.getInstance();
	    String formattedPrice = nf.format(price);
	    String s = String.format("%d\t%s\t%.2f\t%s", no, species, weight, formattedPrice);
	    return s;
	}
	
	public PetVO(String line) {
		String[] token = line.split("\\|");
		setNo(Integer.parseInt(token[0]));
		setSpecies(token[1]);
		setWeight(Float.parseFloat(token[2]));
		setPrice(Integer.parseInt(token[3]));
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getSpecies() {
		return species;
	}
	public void setSpecies(String species) {
		this.species = species;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
}

