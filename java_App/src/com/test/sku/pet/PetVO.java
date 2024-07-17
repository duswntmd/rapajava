package com.test.sku.pet;

public class PetVO {
	
	//Pet(no, species(품종), weight, price)
	private int no;
	private String species;
	private String weight;
	private String price;
	
	public PetVO() {}
	
	public PetVO(int no) {
		setNo(no);
	}
	
	@Override
	public boolean equals(Object obj) {
		PetVO other = (PetVO)obj;
		return this.getNo() == other.getNo();
	}
	@Override
	public String toString() {
		String s = String.format("%d\t%-22s\t%s\t%s", 
				no,species,weight,price);
		return s;
	}
	
	public PetVO(String line) {
		String[] token = line.split("\\|");
		setNo(Integer.parseInt(token[0]));
		setSpecies(token[1]);
		setWeight(token[2]);
		setPrice(token[3]);
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
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
	
}

