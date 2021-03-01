package com.yc.flower.bean;

import java.io.Serializable;

public class Category implements Serializable{

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
        this.cname = cname == null ? null : cname.trim();
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro == null ? null : intro.trim();
    }
}