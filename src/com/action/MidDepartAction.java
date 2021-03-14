package com.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.domain.*;
import com.opensymphony.xwork2.ActionContext;
import com.utils.DBUtils;

public class MidDepartAction {
	
	private Session session=DBUtils.openSession();
	private Integer currentpage;//当前页
	private Integer total;//总记录数
	private Integer first;
	private Integer last;
	private String method;
	private Integer totalpage;
	public String execute()throws Exception{
		
		//获取当前用户
		ActionContext ac = ActionContext.getContext();
		Integer id = (Integer) ac.getSession().get("id");
		
		Transaction transaction2=session.beginTransaction();	
		Query query2=session.createQuery("from Volunteers v where v.id = ?");
		query2.setInteger(0, id);
		Volunteers volunteers = (Volunteers) query2.uniqueResult();
		Depart depart = volunteers.getDepart();
		//depart.getDid();
		System.out.println(depart);
		ServletActionContext.getRequest().setAttribute("volunteers", volunteers);
		//ServletActionContext.getRequest().setAttribute("depart", depart);
		
		transaction2.commit();
		
		Transaction transaction=session.beginTransaction();	
		
		//Query query1=session.createQuery("select count(*) from Depart where d.dstate <> 1");
		Query query1=session.createQuery("from Depart d where d.dstate = 0");
		List<Depart> list1=query1.list();
		//total=((Long)query1.uniqueResult()).intValue();
		total=list1.size();
		System.out.println("total"+total);
		transaction.commit();
		
		//Page page=new Page();
		//page.setTotal(total);
		//PageDao pagedao=new PageDao(page);
      	// int totalpage;//总页数
      	if(total%9 == 0){//总记录数可以被9整除
      	 	totalpage = total/9;
      	 }else{
      	 	totalpage = total/9+1;
      	 }
		//page.setTotalpageByTotal(total);
		
      	//System.out.println("totalpage"+totalpage);
		//ActionContext ac = ActionContext.getContext();
		if(ServletActionContext.getRequest().getAttribute("currentpage")==null){	//第一次点击查看所有部门信息时，默认从第一条开始		
					//设置当前页为第一页
			currentpage=1;
			//ServletActionContext.getRequest().setAttribute("currentpage", currentpage);
			
		}else{//点击上一页下一页的情况
			currentpage=(Integer) ServletActionContext.getRequest().getAttribute("currentpage");
			
		}
		System.out.println("currentpage"+currentpage);
		if(ServletActionContext.getRequest().getParameter("method")==null){
			
			method="0";
			//return "error";
			
		}else{
			method=ServletActionContext.getRequest().getParameter("method");
		}
		if("0".equals(method)){//首页
			currentpage=1;
		}else if("1".equals(method)){//上一页
			if(currentpage<=1){
				currentpage=1;
			}else{
				currentpage-=1;
			}
		}else if("2".equals(method)){//下一页
			if(currentpage>=totalpage){
				currentpage=totalpage;
			}else{
				currentpage+=1;
			}
		}else if("3".equals(method)){//尾页
			currentpage=totalpage;
		}
	//	page.setCurrentpage(method);
		ServletActionContext.getRequest().setAttribute("totalpage", totalpage);
		ServletActionContext.getRequest().setAttribute("currentpage", currentpage);
		
		
		
		//设置每页记录的起止数
		first = (currentpage - 1) * 9;
      	
      	//last:每页显示的最后一条数据在集合中的位置             	 
      	if(first + 9 > total){
      		last = total;
      	}else{
      		last = first+9;
      	}
		
      	Transaction transaction1=session.beginTransaction();	
		Query query=session.createQuery("from Depart d where d.dstate <> 1");
		//分页
		query.setFirstResult(first);
		query.setMaxResults(last);
		List<Depart> list=query.list();
		ServletActionContext.getRequest().setAttribute("departlist", list);
		//for(Depart depart:list){
		//	System.out.println(depart.getDname());
		//}
		transaction1.commit();
		session.close();

		
		return "success";
	
	}
}
