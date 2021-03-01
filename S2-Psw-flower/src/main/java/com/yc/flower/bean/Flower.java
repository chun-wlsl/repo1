package com.yc.flower.bean;

import java.util.Date;

public class Flower implements java.io.Serializable{

	private static final long serialVersionUID = 1L;

	private Integer fid;
	private String fname;
	private Double marketPrice;
	private Double discount;//折扣
	private Double shopPrice;
	private String image;
	private Integer cid;//分类
	private Integer isHot;//是否热门
	private Date fdate;//出售日期
	private  Integer fcount;//库存量
	private String advice;//建议
	
	public Integer getFcount() {
		return fcount;
	}
	public void setFcount(Integer fcount) {
		this.fcount = fcount;
	}
	
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
	public Double getMarketPrice() {
		return marketPrice;
	}
	public void setMarketPrice(Double marketPrice) {
		this.marketPrice = marketPrice;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public Double getShopPrice() {
		return shopPrice;
	}
	public void setShopPrice(Double shopPrice) {
		this.shopPrice = shopPrice;
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
	public Integer getIsHot() {
		return isHot;
	}
	public void setIsHot(Integer isHot) {
		this.isHot = isHot;
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
		return "Flower [fid=" + fid + ", fname=" + fname + ", marketPrice=" + marketPrice + ", discount=" + discount
				+ ", shopPrice=" + shopPrice + ", image=" + image + ", cid=" + cid + ", isHot=" + isHot + ", fdate="
				+ fdate + ", fcount=" + fcount + ", advice=" + advice + "]";
	}
	
	
}
