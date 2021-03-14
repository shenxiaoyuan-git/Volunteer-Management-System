package com.action;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.domain.Notices;
import com.utils.DBUtils;

public class AddNoticesAction {

	private Notices notices=new Notices();
	private Session session=DBUtils.openSession();
	
	
	public String execute()throws Exception{
		System.out.println(notices.getNtitle());
		Transaction transaction=session.beginTransaction();	

		session.save(notices);
		
		transaction.commit();
		session.close();
		return "success";
	
	}


	public Notices getNotices() {
		return notices;
	}


	public void setNotices(Notices notices) {
		this.notices = notices;
	}
	
	
	
}
