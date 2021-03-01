package com.yc.flower.bean;

import java.io.Serializable;

public class Administrator implements Serializable{

	private static final long serialVersionUID = 1L;
	
    private Integer aid;

    private String aname;

    private String apwd;

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
        this.aname = aname == null ? null : aname.trim();
    }

    public String getApwd() {
        return apwd;
    }

    public void setApwd(String apwd) {
        this.apwd = apwd == null ? null : apwd.trim();
    }
}