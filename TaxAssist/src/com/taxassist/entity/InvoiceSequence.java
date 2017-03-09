package com.taxassist.entity;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="INVOICE_SEQUENCE")
public class InvoiceSequence {
	@GenericGenerator(name="maxIdPlusOne" , strategy="increment")

	@Id @GeneratedValue(generator="maxIdPlusOne")
	@Column(name="invoice_seq_id")
	private int invoiceId;
	
	@Column(name="invoice_seq_prefix")
	private String invoiceSeqPrefix;
	
	@Column(name="last_used_number")
	private int lastUsedNumber;

	public int getLastUsedNumber() {
		return lastUsedNumber;
	}

	public void setLastUsedNumber(int lastUsedNumber) {
		this.lastUsedNumber = lastUsedNumber;
	}

	public int getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getInvoiceSeqPrefix() {
		return invoiceSeqPrefix;
	}

	public void setInvoiceSeqPrefix(String invoiceSeqPrefix) {
		this.invoiceSeqPrefix = invoiceSeqPrefix;
	}

	
}
