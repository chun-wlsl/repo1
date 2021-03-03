package com.yc.flower.dao;

import com.yc.flower.bean.Cart;
import com.yc.flower.bean.CartExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CartMapper {
    long countByExample(CartExample example);

    int deleteByExample(CartExample example);

    int deleteByPrimaryKey(Integer ciid);

    int insert(Cart record);

    int insertSelective(Cart record);

    List<Cart> selectByExample(CartExample example);

    Cart selectByPrimaryKey(Integer ciid);

    int updateByExampleSelective(@Param("record") Cart record, @Param("example") CartExample example);

    int updateByExample(@Param("record") Cart record, @Param("example") CartExample example);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);
    
    int update1(int count, Integer uid, int fid);

	List<?> selectCart(Integer uid);

	int upCart(Integer uid, int fid, int count);

	int clearCart(Integer uid);

	int deleteCart(int fid);
}