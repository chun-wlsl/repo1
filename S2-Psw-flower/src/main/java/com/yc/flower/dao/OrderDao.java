package com.yc.flower.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import com.yc.flower.bean.Order;
import com.yc.flower.bean.User;

@Repository
public class OrderDao extends BaseDao{

	/**
	 * 新增订单主表
	 * @param orders
	 * @return 
	 * @return 
	 */
	public int insertOrder(Order order) {
		String sql="insert into order values(null,?,?,?,now(),null,?,?)";
		KeyHolder kh = new GeneratedKeyHolder();
		jt.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql, new String[] {"oid"});
				ps.setObject(1, order.getUid());
				ps.setObject(2, order.getName());
				ps.setObject(3, order.getTotal());
                //ps.setObject(4, order.getState());
				ps.setObject(4, order.getAddr());
				ps.setObject(5, order.getPhone());
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
	
	
	
	
	
	//查询所有的订单（主表)
	
	public  List<Order> selectAllOrder(){
		String sql="select * from order";
		return jt.query(sql,OrderRowMapper);
	}
	 RowMapper<Order> OrderRowMapper=new RowMapper<Order>() {
			
			@Override
			public Order mapRow(ResultSet rs,int rowNumm) throws SQLException {
				Order order =new Order();
				order.setUid(rs.getInt("uid"));
				order.setName(rs.getString("name"));
				order.setTotal(rs.getDouble("total"));
				order.setOtime(rs.getDate("otime"));
				order.setState(rs.getInt("state"));
                 order.setAddr(rs.getString("addr"));
                 order.setPhone(rs.getString("phone"));
				return order;
			}
		};

	//查询uid用户的订单详情
	public List<Map<String,Object>> selectOrders(Integer uid) {
		return jt.queryForList("select * from order a "
				+ "left join orderitem b on a.oid=b.oid"
				+ " left join flower c on b.fid=c.fid "
				+ "where a.uid=?", uid);
	}
}
