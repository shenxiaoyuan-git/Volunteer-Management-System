package com.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.domain.Depart;
import com.domain.Volunteers;
import com.opensymphony.xwork2.ActionContext;
import com.utils.DBUtils;


/**
 * 1.获取点击按钮或链接调用action时传递过来的参数
 * 2.参数为1时，获取所有用户信息，跳转到listuser.jsp页面
 * 3.参数为2时，获取指定用户的信息，是弹出一个小的页面，显示用户的信息
 * @author Administrator
 *
 */
public class ListUserAction {
	private Session session=DBUtils.openSession();
	public String execute()throws Exception{

		//获取点击按钮或链接调用action时传递过来的参数
		String state=ServletActionContext.getRequest().getParameter("state");
		if("1".equals(state)){
		
		//ActionContext ac = ActionContext.getContext();
		Transaction transaction=session.beginTransaction();	
		Query query=session.createQuery("from Volunteers v where v.state = ? or v.state = ? ");
		query.setInteger(0, 0);
		query.setInteger(1, 1);
		List<Volunteers> list=query.list();
		ServletActionContext.getRequest().setAttribute("vollist", list);
		
		for(Volunteers v:list){
			Depart depart=v.getDepart();
			//System.out.println(depart);
			if(depart!=null){
				System.out.println(depart.getDname());
			}else{
				System.out.println("hehe");
			}
			
		}
		transaction.commit();
		session.close();
		return "success";
		
		}else if("2".equals(state)){
			
			//获取指定的id
			String rid=ServletActionContext.getRequest().getParameter("id");
			Integer id;
			if(rid!=null){
				id=Integer.valueOf(rid);
			}else{
				return "error";
			}
			//ActionContext ac = ActionContext.getContext();
			Transaction transaction=session.beginTransaction();	
			
			Query query=session.createQuery("from Volunteers v where id = ?");
			query.setInteger(0, id);
			Volunteers volunteer=(Volunteers) query.uniqueResult();
			ServletActionContext.getRequest().setAttribute("volunteer", volunteer);
	
			transaction.commit();
			session.close();
			return "userinfo";
		}else{
			return "error";
		}
	
	}
}
