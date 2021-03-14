package com.action;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.domain.Depart;
import com.domain.Volunteers;
import com.utils.DBUtils;
/**
 * 
 * 删除用户，与删除部门类似，获取用户id删除后转到中间action显示本页面
 * @author Administrator
 *
 */
public class DeleteUserAction {
	private Integer id;
	private Session session=DBUtils.openSession();
	public String execute()throws Exception{
		

		String rid=ServletActionContext.getRequest().getParameter("id");
		if(rid!=null){
			//当不为空时转为Integer类型id
			id=Integer.valueOf(rid);
		}
		Transaction transaction=session.beginTransaction();	
		//根据用户id来查找对应的Volunteers对象
		Query query=session.createQuery("from Volunteers v where v.id = ?");
		query.setInteger(0, id);
		Volunteers volunteer = (Volunteers) query.uniqueResult();
		//删除
		volunteer.setState(2);
		session.update(volunteer);
		transaction.commit();
		session.close();
		return "success";
		
	}
}
