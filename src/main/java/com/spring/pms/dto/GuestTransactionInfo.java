package com.spring.pms.dto;

import java.util.Date;

public class GuestTransactionInfo {
	private int id;
	private Date transactionDate;
	private String description;
	private String referenceNumber;
	private String transactionId;
	private double charges;
	private GuestStayInfo guestStay;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getReferenceNumber() {
		return referenceNumber;
	}
	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public double getCharges() {
		return charges;
	}
	public void setCharges(double charges) {
		this.charges = charges;
	}
	public GuestStayInfo getGuestStay() {
		return guestStay;
	}
	public void setGuestStay(GuestStayInfo guestStay) {
		this.guestStay = guestStay;
	}
}
