package com.action;

import java.sql.Timestamp;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.domain.Depart;
import com.domain.Notices;
import com.opensymphony.xwork2.ActionContext;
import com.utils.DBUtils;

/**
 * 
 * 公告管理的中间层action
 * 点击查看所有公告的链接后，由该action执行
 * 从数据库中查找所有的公告信息
 * 将信息存储在上下文的request对象中，转到listnotices.jsp页面，显示所有信息
 * @author Administrator
 *
 */
public class MidNoticesAction {

	private Session session=DBUtils.openSession();
	public String execute()throws Exception{

		//ActionContext ac = ActionContext.getContext();
		Transaction transaction=session.beginTransaction();	
		Query query=session.createQuery("from Notices order by datetime desc");
		List<Notices> list=query.list();
		//ServletActionContext.getRequest().setAttribute("departlist", list);
//for(Notices notices:list){
//	System.out.println(notices.getNtitle()+","+notices.getDatetime());
//	Timestamp datetime=notices.getDatetime();
//	if(datetime!=null){
//	System.out.println(datetime.getTime());
//	}
//}
		
		ServletActionContext.getRequest().setAttribute("noticeslist", list);
		transaction.commit();
		session.close();

		
		return "success";
	
	}
}
