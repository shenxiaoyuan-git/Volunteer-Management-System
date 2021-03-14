package com.action;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.domain.Password;
import com.domain.Volunteers;
import com.opensymphony.xwork2.ActionContext;
import com.utils.DBUtils;

public class UpdatePasswordAction {
	
	private Session session=DBUtils.openSession();
	private String password,newpassword,confirmpassword;
	private Password p=new Password();
	
	public String execute(){
		
		//获取当前用户
		ActionContext ac = ActionContext.getContext();
		Integer id =(Integer) ac.getSession().get("id");
		Transaction transaction=session.beginTransaction();	
		Query query=session.createQuery("from Volunteers v where v.id = ?");
		query.setInteger(0, id);
		Volunteers volunteer1=(Volunteers) query.uniqueResult();
		
	//	String password = (String) ServletActionContext.getRequest().getAttribute("password");		
	//	String newpassword = (String) ServletActionContext.getRequest().getAttribute("newpassword");		
	//	String confirmpassword = (String) ServletActionContext.getRequest().getAttribute("confirmpassword");
		
		password = p.getPassword();
		
		newpassword = p.getNewpassword();
		
		confirmpassword = p.getConfirmpassword();
		
		if(volunteer1.getPassword().equals(password)){
			
			//System.out.println("password"+password+"new"+newpassword+"confirm"+confirmpassword);
			
			volunteer1.setPassword(newpassword);
			
			session.update(volunteer1);
			
			transaction.commit();
			session.close();
			
			
			
			return "success";
			
		}else{
			//原始密码不正确
			return "error";
		}
		
		
		
		
	}

	public Password getP() {
		return p;
	}

	public void setP(Password p) {
		this.p = p;
	}
	

}
