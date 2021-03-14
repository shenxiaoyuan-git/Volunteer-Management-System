package com.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.domain.Activities;
import com.domain.Varecord;
import com.domain.Volactivities;
import com.domain.Volunteers;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.utils.DBUtils;

/**
 * 用户申请活动，点击申请按钮后执行该Action
 * 1.获取对应活动的aid，根据aid查询对应的活动
 * 2.获取当前用户的id，根据id查询对应的志愿者
 * 3.向va表中插入数据，（通过Volactivities）并设置状态为0（未审核）
 * @author Administrator
 *
 */

public class ApplyActivitiesAction extends ActionSupport{

	//注意：1.得先确定该用户是否已经申请或参加了该活动 2.防止时间冲突，不可申请已经结束的活动，这个在jsp页面实现
	private Activities activities=new Activities();
	private Session session=DBUtils.openSession();
	private Integer aid;//活动
	private Integer id;//志愿者
	private Volactivities va=new Volactivities();
	private Varecord varecord=new Varecord();
	
	public String execute()throws Exception{
				
		//1.获取申请的活动aid
		String raid=ServletActionContext.getRequest().getParameter("aid");	
		System.out.println(raid);
		if(raid!=null){
			//当部门号不为空时转为Integer类型did
			aid=Integer.valueOf(raid);
		}else{
			return "error";
		}
		
		//2.获取申请活动的志愿者id
		ActionContext actionContext = ActionContext.getContext();
		Map session1 = actionContext.getSession();//获取会话
		
		if(session1.get("id")!=null){
			id=(Integer) session1.get("id");
		}else{
			return "error";//用户id获取失败，为空
		}
		
		//3.根据id查询对应的对象
		//3.1查询活动
		Transaction transaction=session.beginTransaction();	

		//if(volactivities==null){
		
		
		Query query=session.createQuery("from Activities a where a.aid = ?");
		query.setInteger(0, aid);
		//根据页面传来的aid值从数据库中获取对应的Activities对象
		Activities activities= (Activities) query.uniqueResult();		
		System.out.println(activities+"这是ApplyActivities...中获取的活动");
		
		//3.2查询志愿者
		Query query1=session.createQuery("from Volunteers v where v.id = ?");
		query1.setInteger(0, id);
		//根据页面传来的aid值从数据库中获取对应的Activities对象
		Volunteers volunteers = (Volunteers) query1.uniqueResult();		
		System.out.println(activities+"这是ApplyActivities...中获取的志愿者");
		
		//4.判断数据库是否存在同样数据，即是否已经申请或参加该项活动
		//String hql = "from Volactivities v where v.volunteers.id = ?";
		//Query query2=session.createQuery(hql);
		Query query2=session.createQuery("from Volactivities va where va.volunteers.id = ? and va.activities.aid = ?");
		query2.setInteger(0, volunteers.getId());
		query2.setInteger(1, activities.getAid());
		//List<Volactivities> list=query2.list();
		Volactivities volactivities= (Volactivities) query2.uniqueResult();
		//for(Volactivities volactivities1:list){
			//System.out.println(volactivities1);
		//}
		/*boolean flag=true;//没有存在该记录
		Query query2=session.createQuery("from Volactivities");
		List list=query2.list();
		for(Volactivities va:list){
			if(va.getVolunteers().getId()==volunteers.getId()){
				if(va.getActivities().getAid()==activities.getAid()){
					flag=false;//存在
					break;
				}
			}
		}*/
		
	//	System.out.println(list.get(0));
		//System.out.println(list.get(1));
		/*
		SQLQuery query2=session.createSQLQuery("select * from va where va.id = "+volunteers.getId()+"and va.aid = "+activities.getAid());
		System.out.println(volunteers.getId()+","+activities.getAid());
		query2.addEntity(Volactivities.class);
		List<Volactivities> list=query.list();
		System.out.println("volactivities"+list.get(0));
		*/
		System.out.println("list"+volactivities);
		if(volactivities==null){
		//5.插入数据
		va.setActivities(activities);
		va.setVolunteers(volunteers);
		va.setState(0);
		
		session.save(va);
		
		//更新活动记录日志
		//1.活动
		varecord.setActivities(activities);
		//2.操作用户
		varecord.setVvolunteers(volunteers);
		//3.记录状态
		varecord.setState(3);
		//4.无申请人
		//5.时间不赋值，默认为当前时间
		session.save(varecord);

		transaction.commit();
		session.close();
		return "success";
		}else{
			System.out.println("已经参加或申请该活动");
			return "error";//最好是返回当前页面
		}
		

	
	}

}
