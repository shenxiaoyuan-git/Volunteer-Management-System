package com.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.domain.*;
import com.utils.DBUtils;

/**
 * 查看当前待审核申请活动的中间层
 * 从va表中查询state为0的数据显示
 * @author Administrator
 *
 */
public class MidAuditActivitiesAction {
		
	private Session session=DBUtils.openSession();
	public String execute()throws Exception{
		
		Transaction transaction=session.beginTransaction();	
		Query query=session.createQuery("from Volactivities where state != 1 order by applydate desc");
		List<Volactivities> list=query.list();
		for(Volactivities va:list){
			
			System.out.println("活动"+va.getActivities().getAname()+",姓名"+va.getVolunteers().getName());
			
		}
		ServletActionContext.getRequest().setAttribute("valist", list);
		transaction.commit();
		session.close();
		return "success";
	
	}

}
