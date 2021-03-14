package com.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.domain.Notices;
import com.domain.Opinion;
import com.utils.DBUtils;

public class ListOpinionAction {
	
	private Session session=DBUtils.openSession();
	
	
	public String execute(){
		
		Transaction transaction=session.beginTransaction();	
		Query query=session.createQuery("from Opinion order by date desc");
		List<Opinion> list=query.list();
		
		ServletActionContext.getRequest().setAttribute("opinionlist", list);
		transaction.commit();
		session.close();

		
		return "success";
	}
	

}
