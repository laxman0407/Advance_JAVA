package com.tech.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Employee {
	
	@Id
	
	private int eId;
	
	private String eName;
	
	private double eSalary;
	
	private String eAddrs;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int geteId() {
		return eId;
	}

	public void seteId(int eId) {
		this.eId = eId;
	}

	public String geteName() {
		return eName;
	}

	public void seteName(String eName) {
		this.eName = eName;
	}

	public double geteSalary() {
		return eSalary;
	}

	public void seteSalary(double eSalary) {
		this.eSalary = eSalary;
	}

	public String geteAddrs() {
		return eAddrs;
	}

	public void seteAddrs(String eAddrs) {
		this.eAddrs = eAddrs;
	}

}
