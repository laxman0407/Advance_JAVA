package com.tech.operation;

import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.tech.entity.CustInfo;
import com.tech.entity.Customer;
import com.tech.entity.Transction1;
import com.tech.util.HibernateUtil;

public class Operation {

//	SessionFactory sf = HibernateUtil.getSessionFactory();
	Scanner sc = new Scanner(System.in);
	Customer c = new Customer();
	CustInfo cs = new CustInfo();

// account creation procedure.
	public void createAccount() {

		SessionFactory sf = HibernateUtil.getSessionFactory();

//Addhar_number		
		System.out.println("enter your addhar number");
		int d = sc.nextInt();
		if (d >= 100000 && d <= 999999) {
			c.setAddharNo(d);

		} else {
			System.out.println("enter correct addhar number");
		}
// Name
		System.out.println("enter your name");
		String s = sc.next();
		c.setCusName(s);

// address
		System.out.println("enter your address");

		c.setCusAddress(sc.next());

// mobile_number			
		System.out.println("enter your mobile no");
		long y = sc.nextLong();

		c.setCusMobileNumber(y);

// userName		
		System.out.println("enter username");

		c.setUserName(sc.next());

// password			
		System.out.println("enter password");

		c.setPassword(sc.next());

//Deposit_Amount		
		System.out.println("enter amount to Deposit");
		double x = sc.nextDouble();

		if (x >= 500) {
			c.setTotalAmount(x);
		}

		else {
			System.out.println("amount is less than 500");

		}
//Account_number
		System.out.println("enter account number");
		int acc = sc.nextInt();
		c.setAccountNumber(acc);

		c.setDate(new Date());

		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		session.save(c);

// CustInfo entity object sets
		cs.setC_accNumber(acc);
		cs.setC_Name(s);
		cs.setC_mobno(y);
		cs.setC_addharNo(d);

		session.save(cs);

// for transction operation		
// customer object 
		Customer c1 = new Customer();

		c1.setAddharNo(c.getAddharNo());

//  Transction object
		Transction1 t = new Transction1();

		t.setDate(new Date());
		t.setCredit(x);
		t.setBalance(c.getTotalAmount());
		t.setCustomer(c1);

// list of transction object

		List<Transction1> list = new ArrayList<>();
		list.add(t);

		c.setTransction(list);

		session.save(t);

		session.save(c);

		tx.commit();
		session.close();
		sf.close();
	}

// showing account details.
	public void showDetails() {

		while (true) {
			SessionFactory sf = HibernateUtil.getSessionFactory();

			Session session = sf.openSession();

			System.out.println("enter addhar number");
			int x = sc.nextInt();

			Customer c = session.get(Customer.class, x);

			if (c != null) {
				System.out.println("Addhar number=" + c.getAddharNo());
				System.out.println("Account number:" + c.getAccountNumber());
				System.out.println("Name:" + c.getCusName());
				System.out.println("Address:" + c.getCusAddress());
				System.out.println("Mobile Number:" + c.getCusMobileNumber());
				System.out.println("Username:" + c.getUserName());
				System.out.println("Password:" + c.getPassword());
				System.out.println("Balance:" + c.getTotalAmount());
				System.out.println();

				session.close();
				sf.close();
				System.out.println("data get sucessfully");
				break;
			} else {
				System.out.println("you need to create account");
			}
		}

	}

// Deposit money into any account.
	public void deposit() {
		while (true) {
			SessionFactory sf = HibernateUtil.getSessionFactory();

			Session session = sf.openSession();

			Transaction tx = session.beginTransaction();

			System.out.println("enter your addhar number");
			int x = sc.nextInt();

			Customer c = session.get(Customer.class, x);

			if (c != null) {

				System.out.println("enter deposit amount");
				double d = sc.nextDouble();

				if (d > 0) {

					c.setTotalAmount(c.getTotalAmount() + d);
					System.out.println("total bal=" + c.getTotalAmount());
// query for update
					Query<Customer> hql = session
							.createQuery("update Customer set totalAmount=:amt where addharNo=:addharNo");

					hql.setParameter("amt", c.getTotalAmount());
					hql.setParameter("addharNo", x);
					hql.executeUpdate();

	// transction1 object

					Transction1 t = new Transction1();
					t.setCredit(d);
					t.setDate(new Date());
					t.setBalance(c.getTotalAmount());

					c.setAddharNo(c.getAddharNo());
					t.setCustomer(c);

					session.save(t);
					session.save(c);
					tx.commit();
					session.close();
					sf.close();
					break;
				} else {
					System.out.println("enter amount greater than zero");
				}

			} else {
				System.out.println("enter correct addhar number");
			}
		}
	}

// Account balance check.
	public void balanceEnquiry() {

		while (true) {
			SessionFactory sf = HibernateUtil.getSessionFactory();

			Session session = sf.openSession();

			System.out.println("enter addhar number");
			int x = sc.nextInt();
			
			Customer c = session.get(Customer.class, x);
			
			if (c != null) {
				
				c.setTotalAmount(c.getTotalAmount());
				System.out.println("Total available bal=" + c.getTotalAmount());
				session.close();
				sf.close();
				break;
			} else {
				System.out.println("enter correct addhar number");
			}
		}
	}

// for money withdraw from any account.
	public void withdrawMoney() {
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();

		System.out.println("enter addhar number");
		int x = sc.nextInt();
		
		Customer c = session.get(Customer.class, x);
		
		if (c != null) {
			
			if (c.getTotalAmount() > 500) {
				
				System.out.println("enter amount to withdraw");
				double d = sc.nextDouble();
				double d1 = c.getTotalAmount() - d;
				
				if (d1 >= 500) {
					c.setTotalAmount(d1);
					System.out.println("Total available bal=" + c.getTotalAmount());
// query for update					
					Query<Customer> hql = session
							.createQuery("update Customer set totalAmount=:amt where addharNo=:addharNo");
					
					hql.setParameter("amt", c.getTotalAmount());
					hql.setParameter("addharNo", x);
					hql.executeUpdate();

// transction1 object
				Transction1 t = new Transction1();	
					
					t.setDebit(d);
					t.setBalance(d1);
					t.setDate(new Date());
					c.setAddharNo(c.getAddharNo());
					t.setCustomer(c);
					
					session.save(t);
					session.save(c);

					tx.commit();
					session.close();
					sf.close();
				} else {
					System.out.println("enter amount greater than or equal to 500");
				}
			} else {
				System.out.println("insufficent amount in your account");
			}
		} else {
			System.out.println("enter correct addhar number");
		}

	}

// transfer money one account to other account
	public void transferMoney() {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		while (true) {
			Session session = sf.openSession();

			Transaction tx = session.beginTransaction();

			
			double temp = 0;

			System.out.println("enter addhar number");
			int x = sc.nextInt();
			
// transction1 object
			Transction1 t1 = new Transction1();
			
			Customer c = session.get(Customer.class, x);

			if (c != null) {

				System.out.println("get total balance=" + c.getTotalAmount());
				if (c.getTotalAmount() > 500) {
					System.out.println("enter amount to transfer");
					temp = sc.nextDouble();
					double d = c.getTotalAmount() - temp;
					if (d >= 500) {
						c.setTotalAmount(d);
// query update						
						Query<Customer> hql = session
								.createQuery("update Customer set totalAmount=:amt where addharNo=:addharNo");
						hql.setParameter("amt", c.getTotalAmount());
						hql.setParameter("addharNo", x);
						hql.executeUpdate();
						
						t1.setDebit(temp);
						t1.setDate(new Date());
						t1.setBalance(d);
						c.setAddharNo(c.getAddharNo());
						t1.setCustomer(c);

					} else {
						System.out.println("enter amount more than or equal to 500");
					}
				} else {
					System.out.println("insufficient balance");
				}
			}

			else {
				System.out.println("enter correct addhar number");
			}
//
			System.out.println("enter addhar number to transfer money");
			int y = sc.nextInt();
			
			Customer c1 = session.get(Customer.class, y);
			
			if (c1 != null) {
				double k = c1.getTotalAmount() + temp;
				c1.setTotalAmount(k);
				
				System.out.println("total available bal=" + c1.getTotalAmount());
	// query update			
				Query<Customer> hql1 = session
						.createQuery("update Customer set totalAmount=:amt where addharNo=:addharNo");
				
				hql1.setParameter("amt", c1.getTotalAmount());
				hql1.setParameter("addharNo", y);
				hql1.executeUpdate();
				
				// transction1 object
				Transction1 t2 = new Transction1();

				t2.setCredit(temp);
				t2.setDate(new Date());
				t2.setBalance(k);
				c.setAddharNo(c.getAddharNo());
				t2.setCustomer(c);

				session.save(c);
				session.save(t1);
				session.save(t2);

				tx.commit();
				session.close();
				sf.close();
				break;
			} else {
				System.out.println("enter correct addhar number");
			}
		}
	}

	public void accountInfo() {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		System.out.println("addhar  number");
		int x = sc.nextInt();
		
	// Customer info entity object	
		CustInfo cf = session.get(CustInfo.class, x);
		
		try {
			
			if (cf.getC_addharNo() == x) {
				System.out.println("account number verified sucessfully");
				System.out.println("customer have an account");
				System.out.println("customer account number=" + cf.getC_accNumber());
				System.out.println("customer name=" + cf.getC_Name());
				System.out.println("customer mobile number=" + cf.getC_mobno());

			} else {
				System.out.println("you dont have account..");
				System.out.println("first you need to create account ");
			}
		} catch (RuntimeException e) {
			System.out.println("you dont have an account");
		}
		session.close();
		sf.close();
	}

	public void accountStatement() {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
       System.out.println("enter addhar no");
		int x=sc.nextInt();
     Customer cu=  session.get(Customer.class,x );
      for(Transction1 t:cu.getTransction()) {
	 
  
	System.out.println("credit:"+t.getCredit());
	System.out.println("debit:"+t.getDebit());
	System.out.println("balance:"+t.getBalance());
	System.out.println("Date &Time:"+t.getDate());
	System.out.println("addhar no:"+t.getCustomer().getAddharNo());
	System.out.println();
	
 
 }
		session.close();
		sf.close();

	}
}
