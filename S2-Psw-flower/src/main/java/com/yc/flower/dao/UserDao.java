package com.yc.flower.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
	
	
	//根据用户名修改密码
	public void updatePwdByName(String password,String name) {
		String sql="update user set pwd=? where name=?";
		jt.update(sql,password,name);
	}

	//给后台使用
	public int update(User u) {
		String sql="update user set name=?,pwd=?,sex=?,phone=?,addr=?,email=? where uid=?";
		return jt.update(sql, u.getName(), u.getPwd(), u.getSex(),u.getPhone(),u.getAddr(),u.getEmail(),u.getUid());
	}

	//给后台使用
	public int count(Integer uid, String name, String sex) {
		String where = "";
		List<Object> params = new ArrayList<>();
		if(name!=null && name.trim().isEmpty() == false) {
			where += " and name like ?";
			params.add("%" + name + "%");
		}
		if(sex!=null && sex.trim().isEmpty() == false) {
			where += " and sex like ?";
			params.add("%" + sex + "%");
		}
		if(uid != null) {
			where += " and uid = ?";
			params.add(uid);
		}
		String sql = "select null from user where 1=1 " + where;
		return count(sql, params.toArray());
	}

	//给后台使用
	public List<?> queryAllUser(Integer uid, String name, String sex, String page, String rows) {
		String where = "";
		List<Object> params = new ArrayList<>();
		if(name!=null && name.trim().isEmpty() == false) {
			where += " and name like ?";
			params.add("%" + name + "%");
		}
		if(sex!=null && sex.trim().isEmpty() == false) {
			where += " and sex like ?";
			params.add("%" + sex + "%");
		}
		if(uid != null) {
			where += " and uid = ?";
			params.add(uid);
		}
		
		//将字符串转为数字，方便计算
		int ipage = Integer.parseInt(page);
		int irows = Integer.parseInt(rows);
		ipage = (ipage - 1) * 10;
		String sql = "select * from user where 1=1 "
				+ where
				+ " limit ? , ? ";
		params.add(ipage);
		params.add(irows);
		return jt.queryForList(sql, params.toArray());
	}
	
	// 给后台使用
	public int count(String sql, Object... params) {
		String sql1 = "select count(*) cnt from (" + sql + ") a";
		Object cnt = jt.queryForList(sql1, params).get(0).get("cnt");
		int ret = Integer.valueOf("" + cnt);
		return ret;
	}
}
