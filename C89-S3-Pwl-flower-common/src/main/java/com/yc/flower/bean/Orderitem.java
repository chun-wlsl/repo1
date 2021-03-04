package com.yc.flower.bean;

import java.io.Serializable;

public class Orderitem implements Serializable{

	private static final long serialVersionUID = 1L;
	
    private Integer iid;

    private Integer count;

    private Double subtotal;

    private Integer fid;

    private Integer oid;

    //关联对象
    private Order order;
    
    private Flower flower;
    
    public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Flower getFlower() {
		return flower;
	}

	public void setFlower(Flower flower) {
		this.flower = flower;
	}

	public Integer getIid() {
        return iid;
    }

    public void setIid(Integer iid) {
        this.iid = iid;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

	@Override
	public String toString() {
		return "Orderitem [iid=" + iid + ", count=" + count + ", subtotal=" + subtotal + ", fid=" + fid + ", oid=" + oid
				+ ", order=" + order + ", flower=" + flower + "]";
	}
    
    
}