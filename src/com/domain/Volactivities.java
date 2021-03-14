package com.domain;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * 活动与志愿者的中间层，对应中间表va
 * @author Administrator
 *
 */

public class Volactivities {

	private Volunteers volunteers;
	private Activities activities;
	private Integer vaid,state;
	private Set<Varecord> varecord=new HashSet<Varecord>();
	private Timestamp applydate;
	
	public Integer getVaid() {
		return vaid;
	}
	public void setVaid(Integer vaid) {
		this.vaid = vaid;
	}
	
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public void setVolunteers(Volunteers volunteers) {
		this.volunteers = volunteers;
	}
	public Volunteers getVolunteers() {
		return volunteers;
	}
	public Activities getActivities() {
		return activities;
	}
	public void setActivities(Activities activities) {
		this.activities = activities;
	}
	public Set<Varecord> getVarecord() {
		return varecord;
	}
	public void setVarecord(Set<Varecord> varecord) {
		this.varecord = varecord;
	}
	public Timestamp getApplydate() {
		return applydate;
	}
	public void setApplydate(Timestamp applydate) {
		this.applydate = applydate;
	}
	
	
	
}
