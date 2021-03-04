package com.yc.flower.dao;

import com.yc.flower.bean.Order;
import com.yc.flower.bean.Orderitem;
import com.yc.flower.bean.OrderitemExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface OrderitemMapper {
    long countByExample(OrderitemExample example);

    int deleteByExample(OrderitemExample example);

    int deleteByPrimaryKey(Integer iid);

    int insert(Orderitem record);

    int insertSelective(Orderitem record);

    List<Orderitem> selectByExample(OrderitemExample example);

    Orderitem selectByPrimaryKey(Integer iid);

    int updateByExampleSelective(@Param("record") Orderitem record, @Param("example") OrderitemExample example);

    int updateByExample(@Param("record") Orderitem record, @Param("example") OrderitemExample example);

    int updateByPrimaryKeySelective(Orderitem record);

    int updateByPrimaryKey(Orderitem record);

	void insertItems(Integer oid, Integer uid);

	List<Map<String, Object>> queryItem(int oid);

	List<?> queryItembyOid(Integer oid);
}