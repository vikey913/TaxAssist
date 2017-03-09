package com.taxassist.entity;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="ITEM")
public class Item {
	@GenericGenerator(name="maxIdPlusOne" , strategy="increment")

	@Id @GeneratedValue(generator="maxIdPlusOne")
	@Column(name="item_id")
	private int itemId;
	
	@Column(name="quantity")
	private int quantity;
	
	@Column(name="item_base_price")
	private double itemBasePrice;
	
	@Column(name="tax_type")
	private String taxType;
	
	@Column(name="tax_percentage")
	private double taxPercentage;
	
	@Column(name="tax_amount")
	private double taxAmount;
	
	@Column(name="discount_amount")
	private double discountAmount;
	
	@Column(name="item_amount")
	private double itemAmount;
	
	@Column(name="item_description")
	private String itemDescription;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bill_id", nullable = false)
	private Bill bill;

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getItemBasePrice() {
		return itemBasePrice;
	}

	public void setItemBasePrice(double itemBasePrice) {
		this.itemBasePrice = itemBasePrice;
	}

	public String getTaxType() {
		return taxType;
	}

	public void setTaxType(String taxType) {
		this.taxType = taxType;
	}

	public double getTaxPercentage() {
		return taxPercentage;
	}

	public void setTaxPercentage(double taxPercentage) {
		this.taxPercentage = taxPercentage;
	}

	public double getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(double taxAmount) {
		this.taxAmount = taxAmount;
	}

	public double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}

	public double getItemAmount() {
		return itemAmount;
	}

	public void setItemAmount(double itemAmount) {
		this.itemAmount = itemAmount;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	
	
}
