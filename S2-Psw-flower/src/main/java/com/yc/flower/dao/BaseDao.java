package com.yc.flower.dao;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
//该类不需要IOC管理
public abstract  class BaseDao {
      
	@Resource
	protected JdbcTemplate jt;
}
