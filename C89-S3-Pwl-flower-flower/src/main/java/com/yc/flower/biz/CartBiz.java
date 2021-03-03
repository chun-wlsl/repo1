package com.yc.flower.biz;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yc.flower.bean.Cart;
import com.yc.flower.dao.CartMapper;

@Service
public class CartBiz{
	
	@Resource
	private CartMapper cm;
	
	public void addCart(Integer uid, int fid, int count) {
		Cart c = new Cart();
		c.setUid(uid);
		c.setFid(fid);
		c.setCount(count);
		if (cm.update1(count, uid, fid) == 0) {
			cm.insertSelective(c);
		}
	}
	
}
