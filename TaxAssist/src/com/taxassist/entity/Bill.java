package com.taxassist.entity;

import java.util.HashSet; 
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="BILL")
public class Bill {
	@GenericGenerator(name="maxIdPlusOne" , strategy="increment")

	@Id @GeneratedValue(generator="maxIdPlusOne")
	@Column(name="bill_id")
	private int billId;
	
	@Column(name="order_id")
	private String orderId;
	
	@Column(name="order_date")
	private String orderDate;
	
	@Column(name="invoice_number")
	private String invoiceNumber;
	
	@Column(name="invoice_date")
	private String invoiceDate;

	@Column(name="shipping_address")
	private String shippingAddress;
	
	@Column(name="billing_address")
	private String billingAddress;
	
	@Column(name="is_return")
	private char isReturn;
	
	@Column(name="return_invoice_Number")
	private String returnInvoiceNumber;
	
	@Column(name="bill_status")
	private String billStatus;
	
	@Column(name="active")
	private char active;

	@Column(name="created_time")
	private String createdTime;
	
	@Column(name="updated_time")
	private String updatedTime;

	@Column(name="item_count")
	private int itemCount;
	
	@Column(name="shipping_cost")
	private double shippingCost;
	
	@Column(name="total_amount")
	private double totalAmount;
	
	@Column(name="market_place")
	private String marketPlace;
	
	@OneToMany(fetch = FetchType.LAZY)
	private Set<Item> items = new HashSet<Item>(0);

	public Set<Item> getItems() {
		return items;
	}
	public void setItems(Set<Item> items) {
		this.items = items;
	}
	
	public int getBillId() {
		return billId;
	}
	public void setBillId(int billId) {
		this.billId = billId;
	}
	public String getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	public String getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(String updatedTime) {
		this.updatedTime = updatedTime;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String OrderId) {
		this.orderId = OrderId;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String InvoiceNumber) {
		this.invoiceNumber = InvoiceNumber;
	}
	public String getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(String ShippingAddress) {
		this.shippingAddress = ShippingAddress;
	}
	public String getBillingAddress() {
		return billingAddress;
	}
	public void setBillingAddress(String BillingAddress) {
		this.billingAddress = BillingAddress;
	}
	public char getIsReturn() {
		return isReturn;
	}
	public void setIsReturn(char IsReturn) {
		this.isReturn = IsReturn;
	}
	public String getReturnInvoiceNumber() {
		return returnInvoiceNumber;
	}
	public void setReturnInvoiceNumber(String ReturnInvoiceNumber) {
		this.returnInvoiceNumber = ReturnInvoiceNumber;
	}
	public String getBillStatus() {
		return billStatus;
	}
	public void setBillStatus(String BillStatus) {
		this.billStatus = BillStatus;
	}
	public char getActive() {
		return active;
	}
	public void setActive(char Active) {
		this.active = Active;
	}
	public int getItemCount() {
		return itemCount;
	}
	public void setItemCount(int ItemCount) {
		this.itemCount = ItemCount;
	}
	
	public double getShippingCost() {
		return shippingCost;
	}
	public void setShippingCost(double shippingCost) {
		this.shippingCost = shippingCost;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double TotalAmount) {
		this.totalAmount = TotalAmount;
	}
	public String getMarketPlace() {
		return marketPlace;
	}
	public void setMarketPlace(String MarketPlace) {
		this.marketPlace = MarketPlace;
	}

}
