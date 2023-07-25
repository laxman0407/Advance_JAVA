package com.tech.hibernateutil;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

import com.tech.entity.Student;


public class HibernateUtil {
	
	private static StandardServiceRegistry registry;
	
	private static SessionFactory sf;
	
	// method to mapp the connection java to database
	public static SessionFactory getSessionFactory() {
		
		   Map<String, Object> setting=new HashMap<>();
		   setting.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
		   setting.put(Environment.URL, "jdbc:mysql://localhost:3306/hbjava1");
		   setting.put(Environment.USER, "root");
		   setting.put(Environment.PASS, "Root");
		   
		   setting.put(Environment.HBM2DDL_AUTO, "create");
		   setting.put(Environment.SHOW_SQL, "true");
		   setting.put(Environment.DIALECT, "org.hibernate.dialect.MySQL55Dialect");
		
		registry=new StandardServiceRegistryBuilder().applySettings(setting).build();
		
		MetadataSources msd=new MetadataSources(registry).addAnnotatedClass(Student.class);
	    
		Metadata md=msd.getMetadataBuilder().build();
		
		sf=md.getSessionFactoryBuilder().build();
		
		
		return sf;
	}

}
