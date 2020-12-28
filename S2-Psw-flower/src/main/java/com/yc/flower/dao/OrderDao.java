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
	 * 新增订单主表（未支付）
	 * @param orders
	 * @return 
	 * @return 
	 */
	public int insertOrder(Order order) {
		String sql="insert into orders values(null,?,null,?,now(),0,null,null)";
		KeyHolder kh = new GeneratedKeyHolder();
		jt.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql, new String[] {"oid"});
				ps.setObject(1, order.getUid());
				//ps.setObject(2, order.getName());
				ps.setObject(2, order.getTotal());
                //ps.setObject(4, order.getState());
				//ps.setObject(4, order.getAddr());
				//ps.setObject(5, order.getPhone());
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
	
	
	
}
