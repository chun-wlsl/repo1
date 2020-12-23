package com.yc.flower.dao;

import org.springframework.stereotype.Repository;

@Repository
public class OrderDao {

	/**
	 * 新增订单主表
	 * @param orders
	 * @return 
	 */
	public void insertOrder() {
		String sql="insert into order values(null,?,?,?,now(),?,?,?)";
		
	}
}
