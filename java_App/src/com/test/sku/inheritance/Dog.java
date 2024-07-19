package com.test.sku.inheritance;

public class Dog extends Pet {
	float weight;
	float age;
	
	public Dog() {}
	
	@Override
	public String toString() {
		return super.toString() +"\t\t"+ weight +"\t\t"+ age;
	}
	
	public Dog(int price, String species, float weight, float age) {
		super(price, species);
		setWeight(weight);
		setAge(age);
	}
	
	public Dog(String[] token) { 
		super(Integer.parseInt(token[0]), token[1]);
		float weight = Float.parseFloat(token[2]);
        float age = Float.parseFloat(token[3]);  
        setWeight(weight);
        setAge(age);
	}
	
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public float getAge() {
		return age;
	}
	public void setAge(float age) {
		this.age = age;
	}
	
	
}
