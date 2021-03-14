package com.action;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.domain.Volunteers;
import com.utils.DBUtils;

public class ExitDepartAction {

	private Integer id;
	private Session session=DBUtils.openSession();
	
	public String execute(){
		
		
		
		id = Integer.valueOf(ServletActionContext.getRequest().getParameter("id"));
		
		Transaction transaction=session.beginTransaction();	
		
		Query query=session.createQuery("from Volunteers v where v.id = ?");
		query.setInteger(0, id);
		Volunteers volunteers = (Volunteers) query.uniqueResult();	
		
		
		volunteers.setDepart(null);
		volunteers.setDstate(null);
		
		session.update(volunteers);
		
		ServletActionContext.getRequest().setAttribute("method",0);
		
		transaction.commit();
		session.close();
		
		return "success";
	}
	
}
