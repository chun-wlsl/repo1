package com.yc.flower.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.yc.flower.bean.Cart;
import com.yc.flower.bean.Msg;
import com.yc.flower.bean.Topic;

@Repository
public class TopicDao extends BaseDao {

	private RowMapper<Topic> topicRowMapper = new RowMapper<Topic>() {

		@Override
		public Topic mapRow(ResultSet rs, int rowNum) throws SQLException {
			Topic t = new Topic();
			t.setTid(rs.getInt("tid"));
			t.setTitle(rs.getString("title"));
			t.setContent(rs.getString("content"));
			t.setPublishtime(rs.getDate("publishtime"));
			t.setModifytime(rs.getTimestamp("modifytime"));
			t.setUid(rs.getInt("uid"));
			return t;
		}

		
	};
	
	public void insert(Topic t) {
		
	}

	public List<?> queryTopic() {
		String sql = "select b.*,c.* from topic b,user c where c.uid = b.uid";
		return jt.queryForList(sql);
	}
}
