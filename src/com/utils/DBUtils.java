package com.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DBUtils {

	private static SessionFactory sf;
	static{
		Configuration conf=new Configuration().configure();
		sf=conf.buildSessionFactory();
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable(){
			public void run(){
				//sf.close();
			}
		}));
	}
	public static Session openSession(){
		Session session=sf.openSession();
		return session;
	}
	public static Session getCurrentSession(){
		Session session=sf.getCurrentSession();
		return session;
	}
	
	
}
