package com.yc.flower.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.yc.flower.bean.User;
@Repository
public class UserDao extends BaseDao{

//	//查询用户总数
//	public int selectAllUser() {
//		String sql="select count(uid) from user";
//		//String sql ="select uid from user order by uid"
//		return jt.queryForInt(sql);
//	}
//	
	
	//查询所有的用户
	public List<User> selectAllUser(){
		String sql="select * from user";
		return jt.query(sql,UserRowMapper );
	}
	

	//根据用户名查询用户
	public User selectByName(String name) {
		String sql="select * from user where name=?";
		return jt.query(sql, rs->{
			return rs.next() ? UserRowMapper.mapRow(rs,-1) : null;
		},name);
	}
	
	 RowMapper<User> UserRowMapper=new RowMapper<User>() {
		
		@Override
		public User mapRow(ResultSet rs,int rowNumm) throws SQLException {
			User user =new User();
			user.setUid(rs.getInt("uid"));
			user.setName(rs.getString("name"));
			user.setPwd(rs.getString("pwd"));
			user.setSex(rs.getString("sex"));
			user.setCode(rs.getString("code"));
			user.setPhone(rs.getString("phone"));
			user.setAddr(rs.getString("addr"));
			user.setEmail(rs.getString("email"));
			user.setUtime(rs.getDate("utime"));
			return user;
		}
	};

	//添加用户
	public void insert(User user) {
		String sql="insert into user values(null,?,?,?,?,?,?,?,now())";
		jt.update(sql,
				user.getName(),
				user.getPwd(),
				user.getSex(),
				user.getCode(),
				user.getPhone(),
				user.getAddr(),
				user.getEmail());
	}
	
	
	/*public User selectByName(String name) {
		
	}*/
}
