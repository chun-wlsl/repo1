package com.yc.flower.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.yc.flower.bean.User;
import com.yc.flower.biz.BizException;
import com.yc.flower.biz.UserBiz;

@Repository
public class UserDao extends BaseDao{

	@Resource
	private UserBiz ubiz;
	
	//登录
	public User login(String name,String pwd,String code,HttpSession session) throws BizException {
		return ubiz.login(name,pwd,code,session);
		
	}

	//根据用户名查询用户
	public User selectByName(String name) {
		String sql="select * from user where name=?";
		return jt.query(sql, rs->{
			return rs.next() ? UserRowMapper.mapRow(rs,-1) : null;
		},name);
	}
	
	private RowMapper<User> UserRowMapper=new RowMapper<User>() {
		
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
}
