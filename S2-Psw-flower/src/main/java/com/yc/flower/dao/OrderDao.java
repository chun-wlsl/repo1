package com.yc.flower.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import com.yc.flower.bean.Order;

@Repository
public class OrderDao extends BaseDao{

	/**
	 * 新增订单主表
	 * @param orders
	 * @return 
	 * @return 
	 */
	public int insertOrder(Order order) {
		String sql="insert into order values(null,?,?,?,now(),?,?,?)";
		KeyHolder kh = new GeneratedKeyHolder();
		jt.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql, new String[] {"oid"});
				ps.setObject(1, order.getUid());
				ps.setObject(2, order.getName());
				ps.setObject(3, order.getTotal());
				ps.setObject(4, order.getState());
				ps.setObject(5, order.getAddr());
				ps.setObject(6, order.getPhone());
				return ps;
			}
			
		}, kh);
		return kh.getKey().intValue();
		
	}
	
	/**
	 * 新增订单明细
	 * @param orders
	 */
	
	public void insertItems(Order order) {
		String sql = "INSERT INTO orderitem SELECT\n" +
				"	NULL,\n" +
				"	a.count,\n" +
				"	a.count * b.shop_price,\n" +
				"	a.pid,\n" +
				"	?\n" +
				"FROM\n" +
				"	cart a\n" +
				"JOIN flower b ON a.pid = b.pid\n" +
				"WHERE\n" +
				"	a.uid = ?";
		jt.update(sql, order.getOid(), order.getUid());
	}

	public List<Map<String,Object>> selectOrders(Integer uid) {
		return jt.queryForList("select * from order a "
				+ "left join orderitem b on a.oid=b.oid"
				+ " left join flower c on b.pid=c.pid "
				+ "where a.uid=?", uid);
	}
}
