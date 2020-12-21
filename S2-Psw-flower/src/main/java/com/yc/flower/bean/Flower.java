package com.yc.flower.bean;

import java.util.Date;

public class Flower implements java.io.Serializable{

	private static final long serialVersionUID = 1L;

	private Integer fid;
	private String fname;
	private Double market_Price;
	private Double discount;
	private Double shop_Price;
	private String image;
	private Integer cid;
	private Integer is_hot;
	private Date fdate;
	private String advice;
	
	public Integer getFid() {
		return fid;
	}
	public void setFid(Integer fid) {
		this.fid = fid;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public Double getMarket_Price() {
		return market_Price;
	}
	public void setMarket_Price(Double market_Price) {
		this.market_Price = market_Price;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public Double getShop_Price() {
		return shop_Price;
	}
	public void setShop_Price(Double shop_Price) {
		this.shop_Price = shop_Price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public Integer getIs_hot() {
		return is_hot;
	}
	public void setIs_hot(Integer is_hot) {
		this.is_hot = is_hot;
	}
	public Date getFdate() {
		return fdate;
	}
	public void setFdate(Date fdate) {
		this.fdate = fdate;
	}
	public String getAdvice() {
		return advice;
	}
	public void setAdvice(String advice) {
		this.advice = advice;
	}
	
	@Override
	public String toString() {
		return "Flower [fid=" + fid + ", fname=" + fname + ", market_Price=" + market_Price + ", discount=" + discount
				+ ", shop_Price=" + shop_Price + ", image=" + image + ", cid=" + cid + ", is_hot=" + is_hot + ", fdate="
				+ fdate + ", advice=" + advice + "]";
	}
	
	
}
