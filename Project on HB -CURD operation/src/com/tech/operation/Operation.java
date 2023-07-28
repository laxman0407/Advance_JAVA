package com.tech.operation;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.tech.entity.Employee;
import com.tech.util.HibernateUtil;

public class Operation {
	Scanner sc = new Scanner(System.in);

	SessionFactory sf = HibernateUtil.getSeesionFactory();

	// save data into database
	public void saveData() {

		try {

			System.out.println("enter emplyee id");
			int eid = sc.nextInt();
			System.out.println("enter employee Name");
			String ename = sc.next();
			System.out.println("enter employee Address");
			String eaddrs = sc.next();
			System.out.println("enter employee Salary");
			double esalary = sc.nextDouble();

			Session session = sf.openSession();

			Transaction tx = session.beginTransaction();

			Employee emp = new Employee();

			emp.seteId(eid);
			emp.seteName(ename);
			emp.seteAddrs(eaddrs);
			emp.seteSalary(esalary);

			emp.setDate(new Date());

			session.save(emp);
			tx.commit();

			session.close();
			sf.close();

			System.out.println("Data saved Sucessfully");
		} catch (RuntimeException e) {

			System.out.println("Duplicate Key");

		}

	}

	// update data into database
	public void updateData() {
		try {
			System.out.println("Enter employee name");
			String ename = sc.next();
			System.out.println("Enter employee salary");
			double esalary = sc.nextDouble();
			System.out.println("Enter employee id");
			int eid = sc.nextInt();
			Session session = sf.openSession();

			Transaction tx = session.beginTransaction();

			Query hql = session.createQuery("update Employee set eName=:name,eSalary=:salary where eId=:id");

			hql.setParameter("name", ename);
			hql.setParameter("salary", esalary);
			hql.setParameter("id", eid);

			int x = hql.executeUpdate();
			if (x != 0) {

				tx.commit();

				session.close();

				sf.close();
				System.out.println("Data Updated Sucessfully");
			}

			else {
				System.out.println("record not found");
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

	// getAll Data from Database
	public void getAllData() {
		try {
			Session session = sf.openSession();

			Transaction tx = session.beginTransaction();

			List<Employee> emps = session.createQuery("from Employee").list();

			for (Employee emp : emps) {

				System.out.println(emp.geteId());
				System.out.println(emp.geteName());
				System.out.println(emp.geteSalary());
				System.out.println(emp.geteAddrs());
				System.out.println(emp.getDate());
				System.out.println("**************");
			}
			session.close();
			sf.close();
			System.out.println("data get successfully");
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

	// delete data from database
	public void deleteData() {
		try {
			System.out.println("enter employee id");
			int eid = sc.nextInt();

			Session session = sf.openSession();

			Transaction tx = session.beginTransaction();

			Employee emps = session.get(Employee.class, eid);
			if (emps != null) {

				session.delete(emps);

				session.close();

				sf.close();
				System.out.println("data deleted sucessfully");
			} else {
				System.out.println("Record not found");
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}

	}

}
