package com.yc.flower.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.yc.flower.bean.Cart;
import com.yc.flower.bean.Msg;

@Repository
public class MsgDao extends BaseDao {

	private RowMapper<Msg> msgRowMapper = new RowMapper<Msg>() {

		@Override
		public Msg mapRow(ResultSet rs, int rowNum) throws SQLException {
			Msg msg = new Msg();
			msg.setMid(rs.getInt("mid"));
			msg.setContent(rs.getString("content"));
			msg.setPublishtime(rs.getDate("publishtime"));
			msg.setModifytime(rs.getTimestamp("Modifytime"));
			msg.setUid(rs.getInt("uid"));
			msg.setFid(rs.getInt("fid"));
			return msg;
		}
	};
	
	public void insert(Msg m) {
		String sql = "insert into msg values(null,?,now(),now(),?,?)";
		jt.update(sql, m.getContent(), m.getUid(), m.getFid());
	}

	public List<?> queryMsg() {
		String sql = "select a.*,b.* from msg a,user b where a.uid = b.uid order by mid";
		return jt.queryForList(sql);
	}

	public List<?> queryMsgByfid(Integer fid) {
		String sql = "select a.*,b.* from msg a,user b where a.uid = b.uid and fid = ?";
		return jt.queryForList(sql, fid);
	}
}
