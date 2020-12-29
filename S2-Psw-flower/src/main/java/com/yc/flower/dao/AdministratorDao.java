package com.yc.flower.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.yc.flower.bean.Administrator;

@Repository
public class AdministratorDao extends BaseDao {

	private RowMapper<Administrator> administratorRowMapper = new RowMapper<Administrator>() {

		@Override
		public Administrator mapRow(ResultSet rs, int rowNum) throws SQLException {
			Administrator a = new Administrator();
			a.setAid(rs.getInt("aid"));
			a.setAname(rs.getString("aname"));
			a.setApwd(rs.getString("apwd"));
			return a;
		}

	};
	
	public Administrator selectByAname(String aname) {
		String sql="select * from administrator where aname=?";
		return jt.query(sql, rs->{
			return rs.next() ? administratorRowMapper.mapRow(rs,-1) : null;
		},aname);
	}
}
