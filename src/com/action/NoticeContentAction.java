package com.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.domain.Notices;
import com.domain.Volactivities;
import com.domain.Volunteers;
import com.opensymphony.xwork2.ActionContext;
import com.utils.DBUtils;

public class NoticeContentAction {

	private Integer nid;
	private Session session=DBUtils.openSession();
	
	public String execute(){
		
		String rnid = ServletActionContext.getRequest().getParameter("nid");
		if(rnid!=null){
			nid = Integer.valueOf(rnid);
		}else{//传值失败
			return "error";
		}
		
		Transaction transaction=session.beginTransaction();	
		Query query=session.createQuery("from Notices n where n.nid = ? ");
		query.setInteger(0, nid);
		
		Notices notices = (Notices) query.uniqueResult();
		
		
		
		//更新公告记录日志
		
		transaction.commit();
		session.close();

		ServletActionContext.getRequest().setAttribute("notices", notices);
		
		return "success";
	}
	
	
	
}
