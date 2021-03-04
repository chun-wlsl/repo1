package com.yc.flower.bean;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable{

	private static final long serialVersionUID = 1L;
	
    private Integer oid;

    private Integer uid;

    private String name;

    private Double total;

    private Date otime;

    private Integer state;

    private String addr;

    private String phone;
    
	public Integer getOid() {
		return oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Date getOtime() {
		return otime;
	}

	public void setOtime(Date otime) {
		this.otime = otime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Order [oid=" + oid + ", uid=" + uid + ", name=" + name + ", total=" + total + ", otime=" + otime
				+ ", state=" + state + ", addr=" + addr + ", phone=" + phone + "]";
	}
    
}