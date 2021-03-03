package com.yc.flower.bean;

import java.io.Serializable;

public class Cart implements Serializable{

	private static final long serialVersionUID = 1L;
	
    private Integer ciid;

    private Integer uid;

    private Integer fid;

    private Integer count;

    //关联对象
    private User user;
    
    private Flower flower;
    
    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Flower getFlower() {
		return flower;
	}

	public void setFlower(Flower flower) {
		this.flower = flower;
	}

	public Integer getCiid() {
        return ciid;
    }

    public void setCiid(Integer ciid) {
        this.ciid = ciid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

	@Override
	public String toString() {
		return "Cart [ciid=" + ciid + ", uid=" + uid + ", fid=" + fid + ", count=" + count + ", user=" + user
				+ ", flower=" + flower + "]";
	}
    
    
}