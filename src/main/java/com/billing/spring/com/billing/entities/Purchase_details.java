package com.billing.spring.com.billing.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class Purchase_details {

	@Id
	@GeneratedValue
	private int purchase_id;

	private int cus_id;
	private String products;

	@GeneratedValue
	private int sno;

	public Purchase_details() {
	}

	public Purchase_details(int cus_id, String products) {
		this.cus_id = cus_id;
		this.products = products;
	}

	public int getPurchase_id() {
		return purchase_id;
	}

	public void setPurchase_id(int purchase_id) {
		this.purchase_id = purchase_id;
	}

	public int getCus_id() {
		return cus_id;
	}

	public void setCus_id(int cus_id) {
		this.cus_id = cus_id;
	}

	public String getProducts() {
		return products;
	}

	public void setProducts(String products) {
		this.products = products;
	}

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	@Override
	public String toString() {
		return "Purchase_details{" +
				"purchase_id=" + purchase_id +
				", cus_id=" + cus_id +
				", products='" + products + '\'' +
				", sno=" + sno +
				'}';
	}
}
