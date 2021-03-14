package com.action;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.domain.Activities;
import com.domain.Depart;
import com.domain.Volunteers;
import com.opensymphony.xwork2.ActionContext;
import com.utils.DBUtils;

public class ApplyDepartAction {

	private Integer did,id;
	private Session session=DBUtils.openSession();
	
	public String execute(){
		
		did=Integer.valueOf(ServletActionContext.getRequest().getParameter("did"));
		
		id = Integer.valueOf(ServletActionContext.getRequest().getParameter("id"));
		
		Transaction transaction=session.beginTransaction();	
		
		Query query=session.createQuery("from Volunteers v where v.id = ?");
		query.setInteger(0, id);
		Volunteers volunteers = (Volunteers) query.uniqueResult();	
		
		Query query1=session.createQuery("from Depart d where d.did = ?");
		query1.setInteger(0, did);
		Depart depart = (Depart) query1.uniqueResult();	
		
		volunteers.setDepart(depart);
		//volunteers.setDstate(0);
		
		session.update(volunteers);
		
		ServletActionContext.getRequest().setAttribute("method",0);
		
		transaction.commit();
		session.close();
		
		return "success";
	}
	
	
}
