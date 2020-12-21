package com.yc.flower.bean;

public class Category implements java.io.Serializable{

	private static final long serialVersionUID = 1L;

	private Integer cid;
	private String cname;
	private String intro;
	
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	
	@Override
	public String toString() {
		return "Category [cid=" + cid + ", cname=" + cname + ", intro=" + intro + "]";
	}
	
	
	
}
