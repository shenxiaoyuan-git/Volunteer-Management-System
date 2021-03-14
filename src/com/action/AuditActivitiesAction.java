package com.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.domain.Varecord;
import com.domain.Volactivities;
import com.domain.Volunteers;
import com.opensymphony.xwork2.ActionContext;
import com.utils.DBUtils;

public class AuditActivitiesAction {
	
	private Integer vaid;
	private Session session=DBUtils.openSession();
	private Varecord varecord=new Varecord();
	boolean flag=true;
	
	public String execute()throws Exception{
		
		String rvaid = ServletActionContext.getRequest().getParameter("vaid");
		if(rvaid!=null){
			vaid = Integer.valueOf(rvaid);
		}else{//传值失败
			return "error";
		}
		
		Transaction transaction=session.beginTransaction();	
		Query query=session.createQuery("from Volactivities va where va.vaid = ? ");
		query.setInteger(0, vaid);
		Volactivities volactivities = (Volactivities) query.uniqueResult();
		if(volactivities.getState()==0){
			volactivities.setState(1);
			session.update(volactivities);
		}else if(volactivities.getState()==2){
			session.delete(volactivities);
			flag=false;
		}
		
		//获取当前用户
		ActionContext actionContext = ActionContext.getContext();
		Map session1 = actionContext.getSession();//获取会话
		Integer id;
		if(session1.get("id")!=null){
			id=(Integer) session1.get("id");
		}else{
			return "error";//用户id获取失败，为空
		}
		Query query1=session.createQuery("from Volunteers v where v.id = ?");
		query1.setInteger(0, id);
		//根据页面传来的aid值从数据库中获取对应的Activities对象
		Volunteers volunteers = (Volunteers) query1.uniqueResult();		
		
		
		
		//更新活动记录日志
		//1.活动
		varecord.setActivities(volactivities.getActivities());
		//2.操作用户
		varecord.setVolunteers(volunteers);
		//3.记录状态
		if(flag==true){
		varecord.setState(1);//审核通过了参加申请
		}else{
			varecord.setState(5);//审核通过了退出申请
		}
		//4.申请人
		varecord.setVvolunteers(volactivities.getVolunteers());
		//5.时间不赋值，默认为当前时间
		session.save(varecord);

		transaction.commit();
		session.close();

		return "success";
		
	}

}
