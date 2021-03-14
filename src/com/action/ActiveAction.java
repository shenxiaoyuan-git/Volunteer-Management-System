package com.action;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.domain.Depart;
import com.domain.Volunteers;
import com.utils.DBUtils;

public class ActiveAction {
	
	private Session session=DBUtils.openSession();
	
	public String execute(){
		
		String code=ServletActionContext.getRequest().getParameter("code");
		
		Transaction transaction=session.beginTransaction();	
			//根据部门号did来查找对应的Depart对象
		Query query=session.createQuery("from Volunteers v where v.code = ?");
		query.setString(0, code);
			
		Volunteers volunteer = (Volunteers) query.uniqueResult();
		transaction.commit();
			
		if(volunteer==null){
			
			session.close();
			return "error";
		}else{
			
			Transaction transaction1=session.beginTransaction();
			
			volunteer.setState(1);
			volunteer.setCode(null);
			
			session.update(volunteer);
			
			transaction1.commit();
			session.close();
			return "success";
		}
		
		
		
	}

}
