package com.yc.flower.dao;

import com.yc.flower.bean.Order;
import com.yc.flower.bean.OrderExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface OrderMapper {
    long countByExample(OrderExample example);

    int deleteByExample(OrderExample example);

    int deleteByPrimaryKey(Integer oid);

    int insert(Order record);

    int insertSelective(Order record);

    List<Order> selectByExample(OrderExample example);

    Order selectByPrimaryKey(Integer oid);

    int updateByExampleSelective(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByExample(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

	int insertOrder(Order order);

	void update(Order o);

	void updateByOid(Order order);

	List<Map<String, Object>> selectOrders(int uid);

	int mksGetPro(int id);

	int updateState(int state, Integer oid);

	int updateState1(Integer oid);
}