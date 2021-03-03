package com.yc.flower.bean;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
    private Integer uid;

    private String name;

    private String pwd;

    private String sex;

    private String code;

    private String phone;

    private String addr;

    private String email;

    private Date utime;

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
        this.name = name == null ? null : name.trim();
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr == null ? null : addr.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Date getUtime() {
        return utime;
    }

    public void setUtime(Date utime) {
        this.utime = utime;
    }

	@Override
	public String toString() {
		return "User [uid=" + uid + ", name=" + name + ", pwd=" + pwd + ", sex=" + sex + ", code=" + code + ", phone="
				+ phone + ", addr=" + addr + ", email=" + email + ", utime=" + utime + "]";
	}
    
    
}