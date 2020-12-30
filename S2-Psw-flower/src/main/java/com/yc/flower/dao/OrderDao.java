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



@Repository
public class OrderDao extends BaseDao{

	/**
	 * 新增订单主表（未支付）
	 * @param orders
	 * @return 
	 * @return 
	 */
	public int insertOrder(Order orders) {
		String sql = "insert into orders values(null,?,?,?,now(),?,?,?)";
		KeyHolder kh = new GeneratedKeyHolder();
		jt.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql, new String[] {"oid"});
				ps.setObject(1, orders.getUid());
				ps.setObject(2, orders.getName());
				ps.setObject(3, orders.getTotal());
				ps.setObject(4, orders.getState());
				ps.setObject(5, orders.getAddr());
				ps.setObject(6, orders.getPhone());
				
				return ps;
			}
			
		}, kh);
		return kh.getKey().intValue();
		/*jt.update(sql,
				orders.getTotal(),
				orders.getState(),
				orders.getAddr(),
				orders.getPhone(),
				orders.getUid(),
				orders.getName());*/
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
	
   //查找用户uid的所有订单(单表）
	public List<Order>  selectOrder(int uid){
		
		String sql="select * from orders where uid=?";
		return jt.query(sql, OrderRowMapper, uid);
		
	}
	
	
	
       //根据订单编号查询订单详情
   public List<Map<String, Object>> queryItem(int oid){
	String sql="select * from orderitem where oid=?";
	return jt.queryForList(sql, oid);
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

	//查询uid用户的支付订单详情(所有的）
	public List<Map<String,Object>> selectOrders(int uid) {
		return jt.queryForList("select * from orders a "
				+ "left join orderitem b on a.oid=b.oid"
				+ " left join flower c on b.fid=c.fid "
				+ "where  a.uid=?", uid);
	}
	
	
	
	//支付成功
	public int mksGetPro(int  oid) {
		
		String sql="update orders set state=4 where oid=?";
		
		return jt.update(sql, oid);
		
	}
	
	
	/*更改订单状态
	 *   state:0未支付  1支付成功   2 未发货    3已发货     4已收货 
	 *
	 */
	//用户   
	//状态改变  state=1
	public int updateState(int state,int oid) {
		String sql="update orders set state= ? where oid=? ";
		return jt.update(sql,state,oid);
		
	}
	
	


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

	public List<?> queryItembyOid(Integer oid){
		String sql = "select * from  orderitem a left "
				+ "join orders b on a.oid = b.oid left join flower c "
				+ "on a.fid = c.fid where b.oid=?";
		return jt.queryForList(sql, oid);
	}
	
	public int updateByOid(Order o) {
		String sql = "update orders set name=?,state=1,addr=?,phone=?,total=? where oid = ?";
		return jt.update(sql, o.getName(), o.getAddr(), o.getPhone(),o.getTotal(), o.getOid());
	}
	
	public int updateState1(int oid) {
		String sql="update orders set state= state+1 where oid=? ";
		return jt.update(sql,oid);
		
	}
}
