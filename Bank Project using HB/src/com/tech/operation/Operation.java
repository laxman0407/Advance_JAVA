package com.tech.operation;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.tech.entity.Customer;
import com.tech.util.HibernateUtil;

public class Operation {

	SessionFactory sf = HibernateUtil.getSessionFactory();
	Scanner sc = new Scanner(System.in);
	Customer c = new Customer();

// account creation procedure.
	public void createAccount() {
//		while (true) {
			System.out.println("enter your name");
			
				c.setCusName(sc.next());

			

			System.out.println("enter your address");
			try {
				c.setCusAddress(sc.next());

			} catch (Exception e) {
				System.out.println("enter correct address");
			}

			System.out.println("enter your mobile no");
			try {
				c.setCusMobileNumber(sc.nextLong());

			} catch (Exception e) {
				System.out.println("enter correct mobile no");
			}

			System.out.println("enter username");
			try {
				c.setUserName(sc.next());

			} catch (Exception e) {
				System.out.println("enter correct username");
			}

			System.out.println("enter password");
			try {
				c.setPassword(sc.next());

			} catch (Exception e) {
				System.out.println("enter correct password");
			}

			System.out.println("enter amount to Deposit");
			while (true) {
				try {

					
					double x = sc.nextDouble();

					if (x >= 500) {
						c.setTotalAmount(x);
						System.out.println("your acc balance=" + c.getTotalAmount());
						break;

					}

					else {
						System.out.println("amount is less than 500");

					}

				} catch (InputMismatchException e) {
					System.out.println("enter valid amount");
				}
			}
			c.setDate(new Date());

			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
			session.save(c);
			tx.commit();

			session.close();
			sf.close();
			System.out.println("account created sucessfully");

//		}
	}

// showing account details.
	public void showDetails() {
		while (true) {
			Session session = sf.openSession();
			System.out.println("enter account number");
			int x = sc.nextInt();
			Customer c = session.get(Customer.class, x);
			if (c != null) {

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
			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();

			System.out.println("enter your account number");
			int x = sc.nextInt();

			Customer c = session.get(Customer.class, x);
			if (c != null) {
				System.out.println("enter amount");
				double d=sc.nextDouble();
				if (d > 0) {
					c.setTotalAmount(c.getTotalAmount() + d);
					System.out.println("total bal=" + c.getTotalAmount());
					Query<Customer> hql = session
							.createQuery("update Customer set totalAmount=:amt where accountNumber=:acctNo");
					hql.setParameter("amt", c.getTotalAmount());
					hql.setParameter("acctNo", x);
					hql.executeUpdate();
					tx.commit();
					session.close();
					sf.close();
					break;
				} else {
					System.out.println("enter amount greater than zero");
				}

			} else {
				System.out.println("enter correct account number");
			}
		}
	}

// Account balance check.
	public void balanceEnquiry() {
		while (true) {
			Session session = sf.openSession();

			System.out.println("enter account number");
			int x = sc.nextInt();
			Customer c = session.get(Customer.class, x);
			if (c != null) {
				c.setTotalAmount(c.getTotalAmount());
				System.out.println("Total available bal=" + c.getTotalAmount());
				session.close();
				sf.close();
				break;
			} else {
				System.out.println("enter correct account number");
			}
		}
	}

// for money withdraw from any account.
	public void withdrawMoney() {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		System.out.println("enter account number");
		int x = sc.nextInt();
		Customer c = session.get(Customer.class, x);
		if (c != null) {
			if (c.getTotalAmount() > 500) {
				System.out.println("enter amount to withdraw");
				double d=sc.nextDouble();
				double d1 = c.getTotalAmount() - d;
				if (d1 >= 500) {
					c.setTotalAmount(d1);
					System.out.println("Total available bal=" + c.getTotalAmount());
					Query<Customer> hql = session
							.createQuery("update Customer set totalAmount=:amt where accountNumber=:accNo");
					hql.setParameter("amt", c.getTotalAmount());
					hql.setParameter("accNo", x);
					hql.executeUpdate();

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
			System.out.println("enter correct account number");
		}

	}

// transfer money one account to other account
	public void transferMoney() {
		while (true) {
			Session session = sf.openSession();

			Transaction tx = session.beginTransaction();
			 double temp=0;

			System.out.println("enter account number");
			int x = sc.nextInt();
			Customer c = session.get(Customer.class, x);

			if (c != null) {

				System.out.println("get total balance=" + c.getTotalAmount());
				if (c.getTotalAmount() > 500) {
					System.out.println("enter amount to transfer");
					temp=(sc.nextDouble());
					double d = c.getTotalAmount() - temp;
					if (d >= 500) {
						c.setTotalAmount(d);
						Query<Customer> hql = session
								.createQuery("update Customer set totalAmount=:amt where accountNumber=:accNo");
						hql.setParameter("amt", c.getTotalAmount());
						hql.setParameter("accNo", x);
						hql.executeUpdate();

					} else {
						System.out.println("enter amount more than or equal to 500");
					}
				} else {
					System.out.println("insufficient balance");
				}
			}

			else {
				System.out.println("enter correct account number");
			}


			System.out.println("enter account number");
			int y = sc.nextInt();
			Customer c1 = session.get(Customer.class, y);
			if (c1 != null) {
				double k = c1.getTotalAmount() +temp;
				c1.setTotalAmount(k);
				System.out.println("total available bal=" + c1.getTotalAmount());
				Query<Customer> hql1 = session
						.createQuery("update Customer set totalAmount=:amt where accountNumber=:accNo");
				hql1.setParameter("amt", c1.getTotalAmount());
				hql1.setParameter("accNo", y);
				hql1.executeUpdate();

				tx.commit();
				session.close();
				sf.close();
				break;
			} else {
				System.out.println("enter correct account number");
			}
		}
	}
}
