package com.action;
import java.util.List;
import com.opensymphony.xwork2.ActionSupport;
public class TestAction extends ActionSupport{
	   private String userName;
	    private String password;
	    private String message="error";
	    private List list;
	    
	    public String getUserName() {
	        return userName;
	    }
	    public void setUserName(String userName) {
	        this.userName = userName;
	    }
	    public String getPassword() {
	        return password;
	    }
	    public void setPassword(String password) {
	        this.password = password;
	    }
	    
	    public void validate(){
	        if(this.getUserName()==null||this.getUserName().length()==0){
	        	System.out.print("name"+this.getUserName());
	            addFieldError("userName", "用户名不能为空!");
	        }
	    }
	    
	    public String execute() throws Exception{
	        return message;
	    }
}

