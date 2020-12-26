package com.yc.flower.bean;

import java.sql.Timestamp;
import java.util.Date;

public class Msg implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer mid;
	private String content;
	private Date publishtime;
	private Timestamp modifytime;
	private Integer uid;
	private Integer tid;
	
	public Integer getMid() {
		return mid;
	}
	public void setMid(Integer mid) {
		this.mid = mid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getPublishtime() {
		return publishtime;
	}
	public void setPublishtime(Date publishtime) {
		this.publishtime = publishtime;
	}
	public Timestamp getModifytime() {
		return modifytime;
	}
	public void setModifytime(Timestamp modifytime) {
		this.modifytime = modifytime;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	
	@Override
	public String toString() {
		return "Msg [mid=" + mid + ", content=" + content + ", publishtime=" + publishtime + ", modifytime="
				+ modifytime + ", uid=" + uid + ", tid=" + tid + "]";
	}
	
}
