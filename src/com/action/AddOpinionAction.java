package com.action;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.domain.Opinion;
import com.utils.DBUtils;

public class AddOpinionAction {
	
	private Opinion opinion;
	private Session session=DBUtils.openSession();
	
	public String execute(){
		
		Transaction transaction=session.beginTransaction();	
		
		opinion.setDate(new Date());

		session.save(opinion);
		
		//ServletActionContext.getRequest().setAttribute("currentpage",1);
		//ServletActionContext.getRequest().setAttribute("method",0);
		
		transaction.commit();
		session.close();
		return "success";
		
	}

	public Opinion getOpinion() {
		return opinion;
	}

	public void setOpinion(Opinion opinion) {
		this.opinion = opinion;
	}
	
	
	

}
