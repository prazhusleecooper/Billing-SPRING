package com.billing.spring.com.billing.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class Products {

	@Id
	@GeneratedValue
	private int product_ID;

	private String product_Name;
	private float product_price;
	private float tax;
	private float discount_amount;

	public Products() {
		super();
	}

	public Products(int product_ID, String product_Name, float product_price, float tax, float discount_amount) {
		super();
		this.product_ID = product_ID;
		this.product_Name = product_Name;
		this.product_price = product_price;
		this.tax = tax;
		this.discount_amount = discount_amount;
	}

	public int getProduct_ID() {
		return product_ID;
	}

	public void setProduct_ID(int product_ID) {
		this.product_ID = product_ID;
	}

	public String getProduct_Name() {
		return product_Name;
	}

	public void setProduct_Name(String product_Name) {
		this.product_Name = product_Name;
	}

	public float getProduct_price() {
		return product_price;
	}

	public void setProduct_price(float product_price) {
		this.product_price = product_price;
	}

	public float getTax() {
		return tax;
	}

	public void setTax(float tax) {
		this.tax = tax;
	}

	public float getDiscount_amount() {
		return discount_amount;
	}

	public void setDiscount_amount(float discount_amount) {
		this.discount_amount = discount_amount;
	}

	@Override
	public String toString() {
		return "Products{" +
				"product_ID=" + product_ID +
				", product_Name='" + product_Name + '\'' +
				", product_price=" + product_price +
				", tax=" + tax +
				", discount_amount=" + discount_amount +
				'}';
	}
}
