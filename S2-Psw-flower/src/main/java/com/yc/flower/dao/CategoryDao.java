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
	
	
	//通过cid查询一行数据
	public Category queryByCid(int cid) {
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
		
		String sql="insert into category vaules (null,?,?) ";
		
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
	
}
