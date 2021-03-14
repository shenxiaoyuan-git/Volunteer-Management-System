package com.action;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.domain.*;
import com.opensymphony.xwork2.ActionSupport;
import com.utils.DBUtils;
import com.utils.MailUtils;
import com.utils.UUIDUtils;

public class RegisterAction extends ActionSupport{
	
	private Volunteers volunteer=new Volunteers();
	private Session session=DBUtils.openSession();
	
	
	public String execute()throws Exception{
		System.out.println(volunteer.getName());
		
		volunteer.setEstate(0);
		String code = UUIDUtils.getUUID();
		volunteer.setCode(code);
		Transaction transaction=session.beginTransaction();	

		session.save(volunteer);
		
		//发送激活邮件
		MailUtils.sendMail(volunteer.getMail(),code);
		
		transaction.commit();
		session.close();
		return "success";
	
	}

	
	public Volunteers getVolunteer() {
		return volunteer;
	}

	public void setVolunteer(Volunteers volunteer) {
		this.volunteer = volunteer;
	}

}
