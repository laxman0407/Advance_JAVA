package com.tech.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tech.hibernateutil.HibernateUtil;

public class Main {
	
	public static void main(String[] args) {
		
		SessionFactory sf=HibernateUtil.getSessionFactory();
		Session session =sf.openSession();
		Student st=new Student();
		st.setSid(105);
		st.setSname("laxman");
		st.setMobno("95848548");
		st.setMarks(86.00);
		
		session.save(st);
		session.beginTransaction().commit();
		session.close();
		sf.close();
		System.out.println("data saved");
	}

}
