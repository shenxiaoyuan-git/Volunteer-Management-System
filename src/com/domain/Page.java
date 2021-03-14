package com.domain;

public class Page {
	
	private Integer currentpage;//当前页
	private Integer total;//总记录数
	private Integer first;
	private Integer last;
	private Integer totalpage;
	
	public Integer getCurrentpage() {
		return currentpage;
	}
	public void setCurrentpage(Integer currentpage) {
		this.currentpage = currentpage;
	}
	public void setCurrentpage(String method) {
		
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getFirst() {
		return first;
	}
	public void setFirst(Integer first) {
		this.first = first;
	}
	public Integer getLast() {
		return last;
	}
	public void setLast(Integer last) {
		this.last = last;
	}

	public Integer getTotalpage() {
		return totalpage;
	}
	public void setTotalpage(Integer totalpage) {
		this.totalpage = totalpage;
	}
	public void setTotalpageByTotal(Integer total){
      	if(total%9 == 0){//总记录数可以被9整除
      	 	this.totalpage=total/9;
      	 }else{
      	 	this.totalpage=total/9+1;
      	 }
	}

	
	
	

}
