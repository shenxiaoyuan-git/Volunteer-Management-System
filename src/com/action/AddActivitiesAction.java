package com.action;

import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.domain.Activities;
import com.domain.Depart;
import com.domain.Varecord;
import com.domain.Volunteers;
import com.opensymphony.xwork2.ActionContext;
import com.utils.DBUtils;

/**
 * 创建活动
 * 根据页面输入框中的内容向数据库中插入数据
 * 插入成功后向活动记录表中插入数据
 * @author Administrator
 *
 */
public class AddActivitiesAction {
	private Activities activities=new Activities();
	private Session session=DBUtils.openSession();
	private Varecord varecord=new Varecord();
	private ActionContext ac = ActionContext.getContext();
	
	public String execute()throws Exception{
		System.out.println(activities.getAname()+"AddActivitiesAction...");
		Transaction transaction=session.beginTransaction();	

		session.save(activities);
		
		//获取当前用户
		Integer id = (Integer) ac.getSession().get("id");

		Query query=session.createQuery("from Volunteers v where v.id = ? ");
		query.setInteger(0, id);
		Volunteers volunteers=(Volunteers) query.uniqueResult();
		
		System.out.println("id"+volunteers.getId());
		
		//更新活动记录日志
		//1.活动
		varecord.setActivities(activities);
		//2.操作用户
		varecord.setVolunteers(volunteers);
		//3.记录状态
		varecord.setState(0);
		//4.无申请人
		//5.时间不赋值，默认为当前时间
		session.save(varecord);
		
		ServletActionContext.getRequest().setAttribute("astate", 1);
		transaction.commit();
		session.close();
		return "success";
	
	}
	public Activities getActivities() {
		return activities;
	}


	public void setActivities(Activities activities) {
		this.activities = activities;
	}
	
}
