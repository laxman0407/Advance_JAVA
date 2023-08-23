package com.tech.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CustInfo  {
	@Id
    private int c_addharNo;
    
	private int c_accNumber;
	
	private String c_Name;
	
	private long c_mobno;
	
	

	public int getC_addharNo() {
		return c_addharNo;
	}

	public void setC_addharNo(int c_addharNo) {
		this.c_addharNo = c_addharNo;
	}

	public int getC_accNumber() {
		return c_accNumber;
	}

	public void setC_accNumber(int c_accNumber) {
		this.c_accNumber = c_accNumber;
	}

	public String getC_Name() {
		return c_Name;
	}

	public void setC_Name(String c_Name) {
		this.c_Name = c_Name;
	}

	public long getC_mobno() {
		return c_mobno;
	}

	public void setC_mobno(long c_mobno) {
		this.c_mobno = c_mobno;
	}
	
	

}
