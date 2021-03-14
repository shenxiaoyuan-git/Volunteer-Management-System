package com.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.*;

import com.domain.Volunteers;
import com.utils.DBUtils;

public class UpdateUserAction {
	private Volunteers volunteer=new Volunteers();
	private Session session=DBUtils.openSession();
	private Volunteers volunteer1;
	
	public String execute()throws Exception{
		
		//System.out.println(volunteer.getName()+",性别"+volunteer.getSex()+",地址"+volunteer.getAddress()+",学院"+volunteer.getAcademy());
		System.out.println("UpdateUserAction");
		update();
		
		
		
		return "success";
	
	}

	
	public void update(){
		//System.out.println(volunteer1.getAddress()+"13");
		Transaction transaction=session.beginTransaction();	
		//System.out.println(volunteer1.getAddress()+"14");
		Query query=session.createQuery("from Volunteers v where v.name = ?");
		query.setString(0, volunteer.getName());
		volunteer1=(Volunteers) query.uniqueResult();
		volunteer1.setName(volunteer.getName());
		volunteer1.setUsername(volunteer.getUsername());
		volunteer1.setSex(volunteer.getSex());
		volunteer1.setTelephone(volunteer.getTelephone());
		volunteer1.setBirthday(volunteer.getBirthday());
		volunteer1.setAcademy(volunteer.getAcademy());
		volunteer1.setMajor(volunteer.getMajor());
		volunteer1.setPolicy(volunteer.getPolicy());
		volunteer1.setMail(volunteer.getMail());
		volunteer1.setAddress(volunteer.getAddress());
		System.out.println(volunteer1.getAddress()+"12");
		session.update(volunteer1);
		
		transaction.commit();
		session.close();
		
	}
	
	public Volunteers getVolunteer() {
		return volunteer;
	}

	public void setVolunteer(Volunteers volunteer) {
		this.volunteer = volunteer;
	}
}
