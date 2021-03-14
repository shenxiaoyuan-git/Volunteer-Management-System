package com.domain;

import java.sql.Timestamp;
/**
 * state:
 * 	0:审核通过
 * 	1：审核未通过
 * 	2：申请了活动
 * 	3：取消了活动
 * @author Administrator
 *
 */
public class Varecord {
	
	private Integer varid,state;
	private Timestamp date;
	private Volunteers volunteers,vvolunteers;//操作人
	private Activities activities;
	public Integer getVarid() {
		return varid;
	}
	public void setVarid(Integer varid) {
		this.varid = varid;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public Volunteers getVvolunteers() {
		return vvolunteers;
	}
	public void setVvolunteers(Volunteers vvolunteers) {
		this.vvolunteers = vvolunteers;
	}
	public Volunteers getVolunteers() {
		return volunteers;
	}
	public void setVolunteers(Volunteers volunteers) {
		this.volunteers = volunteers;
	}
	public Activities getActivities() {
		return activities;
	}
	public void setActivities(Activities activities) {
		this.activities = activities;
	}
	
	
	
}
