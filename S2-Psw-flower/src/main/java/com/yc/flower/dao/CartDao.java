package com.yc.flower.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.yc.flower.bean.Cart;


@Repository
public class CartDao extends BaseDao{
	
	public List<?> queryCart(Integer uid) throws SQLException {
		String sql = "select flower.*,cart.* from cart,flower " + "where cart.fid=product.fid and cart.uid=?";
		List<?> list = null;
		System.out.println("list==" + list);
		return list;
	}

	public void addCart(int uid, int fid, int count) throws SQLException {
		
		String sql = "update cart set count=count+? where uid=? and fid=?";
		if (jt.update(sql, count, uid, fid) == 0) {
			sql = "insert into cart values(null,?,?,?)";
			jt.update(sql, uid, fid, count);
		}
	}
	
	public void deleteCart(String fid) {
		String sql = "delete from cart where fid=?";

		jt.update(sql, fid);

	}

	public void clearCart(Integer iUid) throws SQLException {
		String sql = "delete from cart where uid = ?";
		jt.update(sql, iUid);
	}

	public void insert(int uid, int fid, int count) {
		jt.update("insert into cart values(null,?,?,?)", uid, fid, count);
	}

	public List<Map<String, Object>> selectCart(Integer uid) {
		return jt.queryForList("select * from cart a" + " left join user b on a.uid=b.uid"
				+ " left join product c on a.fid=c.pid" + " where a.uid=?", uid);
	}

	private RowMapper<Cart> cartRowMapper = new RowMapper<Cart>() {

		@Override
		public Cart mapRow(ResultSet rs, int rowNum) throws SQLException {
			Cart cart = new Cart();
			cart.setCiid(rs.getInt("ciid"));
			cart.setUid(rs.getInt("uid"));
			cart.setFid(rs.getInt("fid"));
			cart.setCount(rs.getInt("count"));
			return cart;
		}
	};

	/**
	 * 查询指定用户的购物车商品总价值
	 * 
	 * @param uid
	 * @return
	 */
	public Double selectTotalByUid(Integer uid) {
		String sql = "SELECT\n" + "	sum(count * b.shop_price)\n" + "FROM\n" + "	cart a\n"
				+ "JOIN product b ON a.fid = b.fid\n" + "WHERE\n" + "	uid = ?";
		return jt.queryForObject(sql, Double.class, uid);
	}

	/**
	 * 根据用户id 删除购物车
	 * 
	 * @param uid
	 */
	public void deleteByUid(Integer uid) {
		jt.update("delete from cart where uid=?", uid);
	}

	/*
	 * 更新购物车
	 */
	public void upCart(int uid, int pid, int count) {
		String sql = "update cart set count=? where uid=? and pid=?";
		jt.update(sql, count, uid, pid);
	}
	
}