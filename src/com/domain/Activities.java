package com.domain;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;
/**
 * 活动类javabean
 * @author Administrator
 *
 */
public class Activities {
	
	private Integer aid;
	private String aname,aaddress,aintro,aleader,atel;
	private Date astart,aend;
	private Set<Volactivities> volactivities=new HashSet<Volactivities>();
	private Set<Varecord> varecord=new HashSet<Varecord>();
	
	public Integer getAid() {
		return aid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	public String getAaddress() {
		return aaddress;
	}
	public void setAaddress(String aaddress) {
		this.aaddress = aaddress;
	}
	public String getAintro() {
		return aintro;
	}
	public void setAintro(String aintro) {
		this.aintro = aintro;
	}
	public Date getAstart() {
		return astart;
	}
	public void setAstart(Date astart) {
		this.astart = astart;
	}
	public Date getAend() {
		return aend;
	}
	public void setAend(Date aend) {
		this.aend = aend;
	}
	public String getAleader() {
		return aleader;
	}
	public void setAleader(String aleader) {
		this.aleader = aleader;
	}
	public String getAtel() {
		return atel;
	}
	public void setAtel(String atel) {
		this.atel = atel;
	}
	public Set<Volactivities> getVolactivities() {
		return volactivities;
	}
	public void setVolactivities(Set<Volactivities> volactivities) {
		this.volactivities = volactivities;
	}
	public Set<Varecord> getVarecord() {
		return varecord;
	}
	public void setVarecord(Set<Varecord> varecord) {
		this.varecord = varecord;
	}

}
