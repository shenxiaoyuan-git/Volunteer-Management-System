package com.action;

import java.sql.Timestamp;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.domain.Activities;
import com.domain.Volactivities;
import com.domain.Volunteers;
import com.opensymphony.xwork2.ActionContext;
import com.utils.DBUtils;

/**
 * 
 * 取消活动申请
 * 1.获取当前用户，和对应的活动
 * 2.获取页面传来的state
 * 3.state=0时，为取消活动申请，从数据库中删除该数据，state=1时，退出活动，设置数据库中数据状态state=2,等审核时再删除该数据
 * @author Administrator
 *
 */

public class CancelApplyAction {
	
	private Integer aid,id;
	private Session session=DBUtils.openSession();
	
	public String execute()throws Exception{
		
		String raid=ServletActionContext.getRequest().getParameter("aid");	
		if(raid!=null){
			//当部门号不为空时转为Integer类型did
			aid=Integer.valueOf(raid);
		}else{
			return "error";
		}
		System.out.println("aid"+aid);
		//2.获取申请活动的志愿者id
		String rid=ServletActionContext.getRequest().getParameter("id");			
		if(rid!=null){
			//当部门号不为空时转为Integer类型did
			id=Integer.valueOf(rid);
		}else{
			return "error";
		}
		System.out.println("id"+id);
		//2.获取申请活动的志愿者id
	/*	ActionContext actionContext = ActionContext.getContext();
		Map session1 = actionContext.getSession();//获取会话
		
		if(session1.get("id")!=null){
			id=(Integer) session1.get("id");
		}else{
			return "error";//用户id获取失败，为空
		}*/
		
		//获取页面传来的state参数，判断是取消申请了还未通过的活动申请，还是退出已经审核通过参加了，但在时间上却还未开始的活动
		String state=ServletActionContext.getRequest().getParameter("state");
		
		
		Transaction transaction=session.beginTransaction();	
		
		
		Query query=session.createQuery("from Activities a where a.aid = ?");
		query.setInteger(0, aid);
		//根据页面传来的aid值从数据库中获取对应的Activities对象
		Activities activities= (Activities) query.uniqueResult();		
		System.out.println(activities+"这是CancelApply...中获取的活动");
		
		//3.2查询志愿者
		System.out.println("id"+id);
		Query query1=session.createQuery("from Volunteers v where v.id = ?");
		query1.setInteger(0, id);
		//根据页面传来的aid值从数据库中获取对应的Activities对象
		Volunteers volunteers = (Volunteers) query1.uniqueResult();		
		System.out.println(volunteers+"这是CancelApply...中获取的志愿者");
		
		
		//4.判断数据库是否存在同样数据，即是否已经申请或参加该项活动
		//String hql = "from Volactivities v where v.volunteers.id = ?";
		//Query query2=session.createQuery(hql);
		Query query2=session.createQuery("from Volactivities va where va.volunteers.id = ? and va.activities.aid = ?");
		query2.setInteger(0, volunteers.getId());
		query2.setInteger(1, activities.getAid());
		//List<Volactivities> list=query2.list();
		Volactivities volactivities= (Volactivities) query2.uniqueResult();
		System.out.println("volactivities"+volactivities);
		
/*		Query query=session.createQuery("from Volactivities va where va.volunteers.id = ? and va.activities.aid = ?");
		query.setInteger(0, id);
		query.setInteger(1, aid);
		//List<Volactivities> list=query2.list();
		Volactivities volactivities= (Volactivities) query.uniqueResult();
		//删除
		System.out.println("volactivities"+volactivities);
		//session.delete(volactivities);
*/
		if("0".equals(state)){//取消申请
		session.delete(volactivities);
		}else{//state=1
			volactivities.setState(2);
			volactivities.setApplydate(new Timestamp(System.currentTimeMillis()));
			session.update(volactivities);
			
		}
		transaction.commit();
		session.close();
		return "success";

	}

}
