package com.yc.flower.dao;
/*
 * 类型
 */

import java.sql.ResultSet;
import java.sql.SQLException;
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

	public void insert(Category f) {
		// TODO Auto-generated method stub
		
	}

	public void update(Category f) {
		// TODO Auto-generated method stub
		
	} 
}
