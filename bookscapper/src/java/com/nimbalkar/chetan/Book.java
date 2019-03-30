package com.nimbalkar.chetan;
public class Book {
	private String category;
	private String name;
	private double price;
	private String condition;

	
	public Book(String category, String name, double price, String condition) {
		this.category = category;
		this.name = name;
		this.price = price;
		this.condition = condition;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getCategory() {
		return category;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public String getCondition() {
		return condition;
	}

}