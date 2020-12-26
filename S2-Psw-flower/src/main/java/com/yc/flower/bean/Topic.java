package com.yc.flower.bean;

import java.sql.Timestamp;
import java.util.Date;

public class Topic implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Integer tid;
	private String title;
	private String content;
	private Date publishtime;
	private Timestamp modifytime;
	private Integer uid;
	
	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	
	@Override
	public String toString() {
		return "Topic [tid=" + tid + ", title=" + title + ", content=" + content + ", publishtime=" + publishtime
				+ ", modifytime=" + modifytime + ", uid=" + uid + "]";
	}
	
	
}
