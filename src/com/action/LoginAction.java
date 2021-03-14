package com.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.domain.*;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.utils.*;
/*
 * 登陆
 * 1.处理登录页面login.jsp传来的参数值，从数据库中查找该用户是否存在以及合法，成功则转向首页
 * 2.设置当前用户于Session中
 */
public class LoginAction extends ActionSupport{

	private Volunteers volunteer=new Volunteers();
	//Volunteers volunteer1;
	//获取页面数据的volunteer对象（struts）
	private Session session=DBUtils.openSession();
	
	private String name;
	private String password;
	private Integer id;
	//private String message;
	private Integer state=1;
	
	public String execute()throws Exception{

		name=volunteer.getName();
		password=volunteer.getPassword();
		state=volunteer.getState();
		//System.out.println("state"+volunteer.getState());
		

		/*if("1".equals(value)){
			String username2 = ServletActionContext.getRequest().getParameter("username");
			System.out.println(username2+"111111111");
			boolean flag = true;
			Map map = new HashMap();
			//JSONObject js = new JSONObject();
			map.put("result", flag);
			HttpServletResponse response = ServletActionContext.getResponse();  
			response.getWriter().println("ok");  
			
		}*/
	/*	boolean flag1=login1();
		if(!flag1){
		
			//String findByNameTip = "exist"; // 存在用户 
			
			String findByNameTip = "noexist"; // 不存在用户 
			
			ServletActionContext.getResponse().getWriter().print(findByNameTip); 
			return null; 
		
		}else{
			Transaction transaction=session.beginTransaction();		
			Query query=session.createQuery("select id from Volunteers v where v.name = ?");
			query.setString(0, name);
			id=(Integer) query.uniqueResult();
			//从数据库中查询的volunteer1对象（hibernate）
			//Volunteers volunteer1 = (Volunteers)query.uniqueResult();
			transaction.commit();

			//获取当前用户，将用户名和id值保存在Session中
			ActionContext ac = ActionContext.getContext();
			//Map app = ac.getApplication();
			ac.getSession().put("name", this.name);
			ac.getSession().put("id", this.id);
			ac.getSession().put("state", this.state);
			session.close();
			return "success";

		}*/
		boolean b=login();
		if(b){	
			
			Transaction transaction=session.beginTransaction();		
			Query query=session.createQuery("select id from Volunteers v where v.name = ?");
			query.setString(0, name);
			id=(Integer) query.uniqueResult();
			//从数据库中查询的volunteer1对象（hibernate）
			//Volunteers volunteer1 = (Volunteers)query.uniqueResult();
			transaction.commit();

			//获取当前用户，将用户名和id值保存在Session中
			ActionContext ac = ActionContext.getContext();
			//Map app = ac.getApplication();
			ac.getSession().put("name", this.name);
			ac.getSession().put("id", this.id);
			ac.getSession().put("state", this.state);
			//ac.getSession().put("currentuser", this.volunteer1);
			//System.out.println(volunteer1.getName()+","+volunteer1.getPassword()+","+volunteer1.getAddress());
			//在jsp页面通过struts2标签获取值
			//<%@ taglib prefix="s" uri="/struts-tags" %>
			//<s:property value="#session.currentuser" />
			session.close();
			return "success";
		}else{
			session.close();
			//HttpServletResponse response = ServletActionContext.getResponse();
			//response.setContentType("text/html;charset=UTF-8");
			//response.getWriter().println("<font color='red'>密码或用户名错误</font>");
			//return NONE;
			return "error";
		}
	
	}

	public boolean login(){
		
		Transaction transaction=session.beginTransaction();	
		
		Query query=session.createQuery("select v.name,v.password from Volunteers v where state = ?");
		if(state!=null){
			query.setInteger(0, state);
		}else{
			query.setInteger(0, 1);
		}
		List<Object[]> list=query.list();
		transaction.commit();
		for(Object[] objs:list){
			//System.out.println(objs[0]+","+objs[1]);
			if(objs[0].equals(name)){
				//System.out.println("用户名存在");
				if(objs[1].equals(password)){
					return true;
				}
				//message="密码错误";
				return false;
				//System.out.println("密码错误");
			}
		}
		//message="不存在该用户！";

		return false;
	}
	
	
	/*public boolean login1(){
		
		Transaction transaction1=session.beginTransaction();	
		
		Query query1=session.createQuery("from Volunteers v where v.name = ?");
		query1.setString(0, volunteer.getName());
		Volunteers volunteer1=(Volunteers) query1.uniqueResult();
		if(volunteer1 == null){
			return false;//bucunzai
		}else{
			return true;
		}

	}*/


	
	public Volunteers getVolunteer() {
		return volunteer;
	}

	public void setVolunteer(Volunteers volunteer) {
		this.volunteer = volunteer;
	}
	
	
}
