package com.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.domain.*;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.utils.DBUtils;

/**
 * 查看所有活动的中间Action
 * 获取点击按钮或链接调用action时传递过来的参数
 * 1.查询所有的活动信息，保存在Request对象中，并转到查看所有活动listactivities.jsp页面
 * 2.查询当前用户的活动信息，保存在Request对象中，并转到查看所有活动myactivities.jsp页面
 * @author Administrator
 *
 */
public class MidActivitiesAction extends ActionSupport{

	private Session session=DBUtils.openSession();	
	//private Activities activities;
	private ActionContext ac = ActionContext.getContext();
	private List<Volactivities> list1;
	public String execute()throws Exception{

		//获取点击按钮或链接调用action时传递过来的参数
		String state=ServletActionContext.getRequest().getParameter("state");
		
		//Map app = ac.getApplication();
		String name1=(String) ac.getSession().get("name");
		Transaction transaction1=session.beginTransaction();	
		Query query1=session.createQuery("from Volunteers v where v.name = ? ");
		query1.setString(0, name1);
		Volunteers volunteers1=(Volunteers) query1.uniqueResult();
		transaction1.commit();
		
		
		if(state==null){
			System.out.println("这里是MidActivitiesAction...");
			Integer astate=(Integer) ServletActionContext.getRequest().getAttribute("astate");
			if(astate!=null){
				state=new Integer(astate).toString();
			}
		}
		System.out.println("这里是MidActivitiesAction");
		Transaction transaction=session.beginTransaction();	
		Query query;
		List<Activities> list=new ArrayList<Activities>();
		boolean flag=true;
		System.out.println("state"+state);
		//当参数为1时查询所有的活动信息，否则查询当前用户的活动信息
		if("1".equals(state)){

			query=session.createQuery("from Activities order by astart desc aend desc");
			list=query.list();
			for(Activities activities:list){
				Set<Volactivities> volactivities=activities.getVolactivities();
				for(Volactivities va:volactivities){
					System.out.println(va.getVolunteers());
				}
				
			
			}
			System.out.println("当前用户名:"+volunteers1.getName());
			ServletActionContext.getRequest().setAttribute("volunteers", volunteers1);
		}else{//state=0
			//获取当前用户名
			flag=false;
			ActionContext ac = ActionContext.getContext();
			//Map app = ac.getApplication();
		/*	String name=(String) ac.getSession().get("name");
			query=session.createQuery("from Volunteers v where v.name = ? ");
			query.setString(0, name);
			Volunteers volunteers=(Volunteers) query.uniqueResult();
			Set<Volactivities> volactivities=volunteers.getVolactivities();//获取该用户所有的中间表对象
			if(volactivities!=null){
				for(Volactivities va:volactivities){
					if(va.getState()==1){//只显示state为1，即已参加的活动，state为0表示正在审核中
					Activities activities=va.getActivities();
					System.out.println(activities.getAname()+"hhahaha");
					list.add(activities);
					}
				}
			}*/
			Integer id = (Integer) ac.getSession().get("id");
			query=session.createQuery("from Volactivities va where va.volunteers = ? order by va.applydate desc");
			query.setInteger(0,id);
			list1=(List<Volactivities>) query.list();
			for(Volactivities va:list1){
				
				Activities activities=va.getActivities();
				Volunteers volunteers = va.getVolunteers();
				System.out.println(activities+"hhahaha");
				System.out.println(volunteers+"hhahaha");
				
				
			}
			//按照活动的开始时间降序排序
			int n = list1.size();
		//	boolean flag1=false;
			Volactivities temp;
			for(int i=0;i<n;i++){
			//	if(flag1==false){
				for(int j=0;j<n-i-1;j++){
					
					if(list1.get(j).getActivities().getAstart().compareTo(list1.get(j+1).getActivities().getAstart())==-1){//小于
						temp=list1.get(j);
						list1.set(j, list1.get(j+1));
						list1.set(j+1, temp);	
				//		flag1=false;
						
					}else if(list1.get(j).getActivities().getAstart().compareTo(list1.get(j+1).getActivities().getAstart())==0&&list1.get(j).getActivities().getAend().compareTo(list1.get(j+1).getActivities().getAend())==-1){//等于
						temp=list1.get(j);
						list1.set(j, list1.get(j+1));
						list1.set(j+1, temp);	
				//		flag1=false;
					}
					
				}
				}
				
			}
			
			
			
		
		
		//System.out.println(list);
		for(Activities a:list){
			System.out.println(a.getAname());
		}
		//ServletActionContext.getRequest().setAttribute("activitieslist", list);
		transaction.commit();
		session.close();
		if(flag){
			ServletActionContext.getRequest().setAttribute("activitieslist", list);
			return "success";
		}else{
			ServletActionContext.getRequest().setAttribute("volactivitieslist", list1);
			return "my";
		}
	
	}

	
}
