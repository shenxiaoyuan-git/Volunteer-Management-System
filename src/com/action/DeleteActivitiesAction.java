package com.action;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.domain.*;
import com.opensymphony.xwork2.ActionSupport;
import com.utils.DBUtils;

public class DeleteActivitiesAction extends ActionSupport{

	private Integer aid;
	private Session session=DBUtils.openSession();
	public String execute()throws Exception{
		
		//Depart depart=(Depart) ServletActionContext.getRequest().getAttribute("depart");
		//rdid:jsp页面传来的部门号，默认String类型
		String raid=ServletActionContext.getRequest().getParameter("aid");
		if(raid!=null){
			//当部门号不为空时转为Integer类型did
			aid=Integer.valueOf(raid);
		}else{
			return "error";
		}
		Transaction transaction=session.beginTransaction();	
		//根据部门号did来查找对应的Depart对象
		Query query=session.createQuery("from Activities a where a.aid = ?");
		query.setInteger(0, aid);
		Activities activities= (Activities) query.uniqueResult();
		//删除
		session.delete(activities);
		System.out.println(activities+"这里是DeleteActivities...");
		ServletActionContext.getRequest().setAttribute("astate", 1);
		transaction.commit();
		session.close();
		return "success";
		
	}
	
}
