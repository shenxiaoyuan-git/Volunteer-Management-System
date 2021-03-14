package com.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Volunteers {

	private Integer id,age,state;
	private String username,name,password,sex,academy,major,policy,address,telephone,mail;
	private Depart depart;
	private Date birthday;
	private Set<Volactivities> volactivities=new HashSet<Volactivities>();
	private Set<Varecord> varecord=new HashSet<Varecord>();
	private Integer dstate,estate;
	private String code;

	
	public Integer getEstate() {
		return estate;
	}
	public void setEstate(Integer estate) {
		this.estate = estate;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAcademy() {
		return academy;
	}
	public void setAcademy(String academy) {
		this.academy = academy;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getPolicy() {
		return policy;
	}
	public void setPolicy(String policy) {
		this.policy = policy;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public Depart getDepart() {
		return depart;
	}
	public void setDepart(Depart depart) {
		this.depart = depart;
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
	public Integer getDstate() {
		return dstate;
	}
	public void setDstate(Integer dstate) {
		this.dstate = dstate;
	}


	
}
