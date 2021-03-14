package com.action;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.domain.*;
import com.utils.DBUtils;
/**
 * 添加部门，通过struts2模式驱动自动获取页面数据至depart对象
 * 使用hibernate对数据库进行插入操作
 * 弹出插入成功后跳转到显示所有部门信息的页面
 * @author Administrator
 *
 */
public class AddDepartAction {
	
	private Depart depart=new Depart();
	private Session session=DBUtils.openSession();
	
	
	public String execute()throws Exception{
		System.out.println(depart.getDname());
		Transaction transaction=session.beginTransaction();	

		depart.setDstate(0);
		session.save(depart);
		
		//ServletActionContext.getRequest().setAttribute("currentpage",1);
		//ServletActionContext.getRequest().setAttribute("method",0);
		
		transaction.commit();
		session.close();
		return "success";
	
	}

	
	public Depart getDepart() {
		return depart;
	}

	public void setDepart(Depart depart) {
		this.depart = depart;
	}
	

}
