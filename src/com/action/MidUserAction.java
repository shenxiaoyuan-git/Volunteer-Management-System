package com.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.domain.Volunteers;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.utils.DBUtils;


/**
 * 点击完善个人信息，实现中间转向
 * 获取当前用户，查找个人信息后显示在完善个人信息页面updateuser.jsp
 * @author Administrator
 *
 */
public class MidUserAction extends ActionSupport{
	private Session session=DBUtils.openSession();
	
	private Volunteers volunteer=new Volunteers();
	public String execute()throws Exception{
		
		//获取当前用户
		ActionContext ac = ActionContext.getContext();
		String name=(String) ac.getSession().get("name");
		System.out.println(name);
		Transaction transaction=session.beginTransaction();	
		Query query=session.createQuery("from Volunteers v where v.name = ?");
		query.setString(0, name);
		volunteer=(Volunteers) query.uniqueResult();
		
		System.out.print("birthday"+volunteer.getBirthday());
		
		ServletActionContext.getRequest().setAttribute("currentuser", this.volunteer);
		
		transaction.commit();
		session.close();
		
		return "success";
	
	}
	public Volunteers getVolunteer() {
		return volunteer;
	}
	public void setVolunteer(Volunteers volunteer) {
		this.volunteer = volunteer;
	}


}
