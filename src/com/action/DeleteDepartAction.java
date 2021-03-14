package com.action;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.domain.Depart;
import com.utils.DBUtils;

/**
 * 删除部门
 * 点击页面删除链接，接收jsp页面传来的对应部门的部门号
 * 通过部门号获取对应的Depart对象并删除
 * 返回
 * @author Administrator
 *
 */

public class DeleteDepartAction {

	private Integer did;
	private Session session=DBUtils.openSession();
	public String execute()throws Exception{
		
		//Depart depart=(Depart) ServletActionContext.getRequest().getAttribute("depart");
		//rdid:jsp页面传来的部门号，默认String类型
		String rdid=ServletActionContext.getRequest().getParameter("did");
		if(rdid!=null){
			//当部门号不为空时转为Integer类型did
			did=Integer.valueOf(rdid);
		}
		Transaction transaction=session.beginTransaction();	
		//根据部门号did来查找对应的Depart对象
		Query query=session.createQuery("from Depart d where d.did = ?");
		query.setInteger(0, did);
		Depart depart=(Depart) query.uniqueResult();
		//删除
		
		depart.setDstate(1);
		session.update(depart);
		//session.delete(depart);
		System.out.println(depart);
		
		transaction.commit();
		session.close();
		return "success";
		
	}
}
