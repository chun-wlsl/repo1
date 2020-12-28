package com.yc.flower.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
	 * 新增订单主表（未支付）
	 * @param orders
	 * @return 
	 * @return 
	 */
	public int insertOrder(Order order) {
<<<<<<< HEAD
		String sql="insert into orders values(null,?,null,?,now(),0,null,null)";
=======
		String sql="insert into orders values(null,?,?,?,now(),?,?,?)";
>>>>>>> branch 'main' of https://github.com/chun-wlsl/repo1.git
		KeyHolder kh = new GeneratedKeyHolder();
		jt.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql, new String[] {"oid"});
				ps.setObject(1, order.getUid());
<<<<<<< HEAD
				//ps.setObject(2, order.getName());
				ps.setObject(2, order.getTotal());
                //ps.setObject(4, order.getState());
				//ps.setObject(4, order.getAddr());
				//ps.setObject(5, order.getPhone());
=======
				ps.setObject(2, order.getName());
				ps.setObject(3, order.getTotal());
                ps.setObject(4, 1);
				ps.setObject(5, order.getAddr());
				ps.setObject(6, order.getPhone());
>>>>>>> branch 'main' of https://github.com/chun-wlsl/repo1.git
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
				"	a.fid,\n" +
				"	?\n" +
				"FROM\n" +
				"	cart a\n" +
				"JOIN flower b ON a.fid = b.fid\n" +
				"WHERE\n" +
				"	a.uid = ?";
		jt.update(sql, order.getOid(), order.getUid());
	}
	
	
	
	
	
	//查询所有的订单（主表)
	
	public  List<Order> selectAllOrder(){
		String sql="select * from orders";
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

	//查询uid用户的为支付订单详情
	public List<Map<String,Object>> selectOrders(int uid) {
		return jt.queryForList("select * from orders a "
				+ "left join orderitem b on a.oid=b.oid"
				+ " left join flower c on b.fid=c.fid "
				+ "where a.state=0 and a.uid=?", uid);
	}
<<<<<<< HEAD
	
	//支付成功
	public int checkState(Order order) {
		String sql="update orders set state=1,name=?,addr=?,phone=? where oid=? ";
		return jt.update(sql,order.getName(),order.getAddr(),order.getPhone(),order.getOid());
		
	}
	
	/*更改订单状态
	 *   state:0未支付  1支付成功   2 未发货    3已发货     4已收货 
	 *
	 */
	//用户   
	//状态改变  state=1
	public int updateState(int state,int oid) {
		String sql="update orders set state= ? where oid=? ";
		return jt.update(sql, state,oid);
		
	}
	
	
=======

	public int update(Order o) {
		String sql = "update orders set uid=?,name=?,total=?,state=?,addr=?,phone=? where oid = ?";
		return jt.update(sql, o.getUid(), o.getName(),o.getTotal(),o.getState(),o.getAddr(),o.getPhone(), o.getOid());
	}

	//给后台使用，查找所有的商品,在flower.html中使用
	public List<?> selectAllOrder(Integer oid, Integer uid, String name, Integer state, String page, String rows) {
		String where = "";
		List<Object> params = new ArrayList<>();
		if(name!=null && name.trim().isEmpty() == false) {
			where += " and name like ?";
			params.add("%" + name + "%");
		}
		if(uid != null) {
			where += " and uid = ?";
			params.add(uid);
		}
		if(state != null ) {
			where += " and state = ?";
			params.add(state);
		}
		if(oid != null ) {
			where += " and oid = ?";
			params.add(oid);
		}
		//将字符串转为数字，方便计算
		int ipage = Integer.parseInt(page);
		int irows = Integer.parseInt(rows);
		ipage = (ipage - 1) * 10;
		String sql = "select * from orders where 1=1 "
				+ where
				+ " limit ? , ? ";
		params.add(ipage);
		params.add(irows);
		return jt.queryForList(sql, params.toArray());
	}

	// 给后台使用
	public int count(Integer oid, Integer uid, String name, Integer state) {
		String where = "";
		List<Object> params = new ArrayList<>();
		if(name!=null && name.trim().isEmpty() == false) {
			where += " and name like ?";
			params.add("%" + name + "%");
		}
		if(uid != null) {
			where += " and uid = ?";
			params.add(uid);
		}
		if(state != null ) {
			where += " and state = ?";
			params.add(state);
		}
		if(oid != null ) {
			where += " and oid = ?";
			params.add(oid);
		}
		
		String sql = "select null from orders where 1=1 " + where;
		return count(sql, params.toArray());
	}
	
	// 给后台使用
	public int count(String sql, Object... params) {
		String sql1 = "select count(*) cnt from (" + sql + ") a";
		Object cnt = jt.queryForList(sql1, params).get(0).get("cnt");
		int ret = Integer.valueOf("" + cnt);
		return ret;
	}
>>>>>>> branch 'main' of https://github.com/chun-wlsl/repo1.git
	
}
