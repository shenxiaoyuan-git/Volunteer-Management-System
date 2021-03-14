package com.action;

import java.sql.Timestamp;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.domain.Notices;
import com.domain.Varecord;
import com.opensymphony.xwork2.ActionContext;
import com.utils.DBUtils;

public class MidVarecordAction {
	
	private Session session=DBUtils.openSession();
	public String execute()throws Exception{
		
		Transaction transaction=session.beginTransaction();	
		Query query=session.createQuery("from Varecord order by date desc");
		List<Varecord> list=query.list();
		//ServletActionContext.getRequest().setAttribute("departlist", list);
		for(Varecord varecord:list){
			//System.out.println("1"+varecord.getVolunteers().getId()+","+varecord.getActivities().getAid()+","+varecord.getDate());
			//System.out.println("2"+varecord.getVvolunteers().getId());
			Timestamp datetime=varecord.getDate();
			if(datetime!=null){
				System.out.println(datetime.getTime());
			}
		}
		
		ServletActionContext.getRequest().setAttribute("varecord", list);
		transaction.commit();
		session.close();

		
		return "success";
		
		
	}

}
