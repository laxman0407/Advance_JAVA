package com.tech.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Customer {

	@Id
	private int addharNo;

	private int accountNumber;

	private String cusName;

	private String cusAddress;

	private long cusMobileNumber;

	private String userName;

	private String password;

	private double totalAmount;
	
	@OneToMany(mappedBy = "customer")
	private List<Transction1> transction;
	
	public List<Transction1> getTransction() {
		return transction;
	}

	public void setTransction(List<Transction1> transction) {
		this.transction = transction;
	}

	public int getAddharNo() {
		return addharNo;
	}

	public void setAddharNo(int addharNo) {
		this.addharNo = addharNo;
	}

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	public String getCusAddress() {
		return cusAddress;
	}

	public void setCusAddress(String cusAddress) {
		this.cusAddress = cusAddress;
	}

	public long getCusMobileNumber() {
		return cusMobileNumber;
	}

	public void setCusMobileNumber(long cusMobileNumber) {
		this.cusMobileNumber = cusMobileNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

}
