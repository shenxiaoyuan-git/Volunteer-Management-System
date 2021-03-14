package com.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * 部门类，与志愿者类为一对多的关系
 * @author Administrator
 *
 */
public class Depart {

	private Integer did,dstate;
	private String dname,dperson,dtel,dintro;
	private Date ddate;
	private Set<Volunteers> volunteers=new HashSet<Volunteers>();
	
	public Integer getDid() {
		return did;
	}
	public void setDid(Integer did) {
		this.did = did;
	}
	public Integer getDstate() {
		return dstate;
	}
	public void setDstate(Integer dstate) {
		this.dstate = dstate;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	
	public String getDperson() {
		return dperson;
	}
	public void setDperson(String dperson) {
		this.dperson = dperson;
	}
	public Date getDdate() {
		return ddate;
	}
	public void setDdate(Date ddate) {
		this.ddate = ddate;
	}
	public String getDtel() {
		return dtel;
	}
	public void setDtel(String dtel) {
		this.dtel = dtel;
	}
	public String getDintro() {
		return dintro;
	}
	public void setDintro(String dintro) {
		this.dintro = dintro;
	}
	public Set<Volunteers> getVolunteers() {
		return volunteers;
	}
	public void setVolunteers(Set<Volunteers> volunteers) {
		this.volunteers = volunteers;
	}
	
	
}
