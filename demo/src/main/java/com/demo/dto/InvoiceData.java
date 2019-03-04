package com.demo.dto;

public class InvoiceData {

	private String ProdName;
	private double price;
	private int quanity;
	private double amount;

	public InvoiceData(String prodName, double price, int quanity, double amount) {
		super();
		ProdName = prodName;
		this.price = price;
		this.quanity = quanity;
		this.amount = amount;
	}

	public String getProdName() {
		return ProdName;
	}

	public void setProdName(String prodName) {
		ProdName = prodName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuanity() {
		return quanity;
	}

	public void setQuanity(int quanity) {
		this.quanity = quanity;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
