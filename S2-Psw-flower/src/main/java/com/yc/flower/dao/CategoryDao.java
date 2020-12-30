package com.yc.flower.dao;
/*
 * 类型
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.yc.flower.bean.Category;




@Repository
public class CategoryDao extends BaseDao{
    //查询所有花分类
	public List<Category> queryCategory(){
		String sql = "select * from category";
		return jt.query(sql,categoryRowMapper);
	}
	
	
	//通过cid查询一行数据
	public Category queryByCid(Integer cid) {
		String sql= "select * from category where cid= ? ";
         return jt.query(sql, rs->{
 			return rs.next() ? categoryRowMapper.mapRow(rs, -1) : null;
 		},cid);
	}
	
	
	RowMapper<Category> categoryRowMapper = new RowMapper<Category>() {

		@Override
		public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
			Category c = new Category();
			c.setCid(rs.getInt("cid"));
			c.setCname(rs.getString("cname"));
			c.setIntro(rs.getString("intro"));
			return c;
		}
	};

	public int  insertCategory(Category c) {
		
		String sql="insert into category values (null,?,?) ";
		
		return jt.update(sql,
				c.getCname(),
				c.getIntro()
				);
	}

	public int update(Category c) {
		String sql="update category set cname=?,intro=? where cid=? ";
		return jt.update(sql,
				c.getCname(),
				c.getIntro(),
				c.getCid()
				);
				
	}


	//给后台使用
	public List<?> selectAllCategory(Integer cid, String cname, String intro, String page, String rows) {
		String where = "";
		List<Object> params = new ArrayList<>();
		if(cname!=null && cname.trim().isEmpty() == false) {
			where += " and cname like ? ";
			params.add("%" + cname + "%");
		}
		if(intro!=null && intro.trim().isEmpty() == false) {
			where += " and intro like ? ";
			params.add("%" + intro + "%");
		}
		if(cid != null ) {
			where += " and cid = ? ";
			params.add(cid);
		}
		
		//将字符串转为数字，方便计算
		int ipage = Integer.parseInt(page);
		int irows = Integer.parseInt(rows);
		ipage = (ipage - 1) * 10;
		String sql = "select * from category where 1=1"
				+ where
				+ " limit ? , ? ";
		params.add(ipage);
		params.add(irows);
		return jt.queryForList(sql, params.toArray());
	}
	
	//给后台使用
	public int count(Integer cid, String cname, String intro) {
		String where = "";
		List<Object> params = new ArrayList<>();
		if(cname!=null && cname.trim().isEmpty() == false) {
			where += " and cname like ? ";
			params.add("%" + cname + "%");
		}
		if(intro!=null && intro.trim().isEmpty() == false) {
			where += " and intro like ? ";
			params.add("%" + intro + "%");
		}
		if(cid != null ) {
			where += " and cid = ? ";
			params.add(cid);
		}
		String sql = "select null from category where 1=1 " + where;
		return count(sql, params.toArray());
	}
	
	//给后台使用
	public int count(String sql, Object... params) {
		String sql1 = "select count(*) cnt from (" + sql + ") a";
		Object cnt = jt.queryForList(sql1, params).get(0).get("cnt");
		int ret = Integer.valueOf("" + cnt);
		return ret;
	}
}
