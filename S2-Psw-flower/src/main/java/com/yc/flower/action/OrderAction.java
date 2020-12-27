package com.yc.flower.action;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yc.flower.bean.Flower;
import com.yc.flower.bean.Order;
import com.yc.flower.bean.Result;
import com.yc.flower.bean.User;
import com.yc.flower.biz.BizException;
import com.yc.flower.biz.OrderBiz;
import com.yc.flower.dao.OrderDao;


@RestController
public class OrderAction {

	@Resource
	private OrderBiz obiz;
	
	@Resource
	private OrderDao odao;
	
	@RequestMapping("order.s")
	public Result pay(Order order, HttpSession session ,Double total,String addr,String phone,String name) throws SQLException {

		try {
			User user = (User) session.getAttribute("loginedUser");
			order.setUid(user.getUid());
			order.setName(name);
			order.setTotal(total);
			order.setAddr(addr);
			order.setPhone(phone);
			obiz.pay(order);
			return Result.success("下单成功!");
		} catch (BizException e) {
			e.printStackTrace();
			return Result.failure(e.getMessage());
		}
	}
	
	//给后台使用
   	@RequestMapping(path="orders.s",params="op=save")
	public Result save(Integer oid, Integer uid, String name, Double total, Integer state, String addr, String phone){
   		Order order = new Order();
   		order.setOid(oid);
   		order.setUid(uid);
		order.setName(name);
		order.setTotal(total);
		order.setState(state);
		order.setAddr(addr);
		order.setPhone(phone);
		try {
			obiz.save(order);
			return new Result(1,"订单保存成功!");
		} catch (BizException e) {
			e.printStackTrace();
			return new Result(0,e.getMessage());
		}
	}
   	
   	//给后台使用，查找所有的商品
   	@RequestMapping("queryAllOrders")
	public Map<String, Object> queryAllOrders(Integer oid, Integer uid, String name, Integer state, String page, String rows){
   		System.out.println("oid:"+oid+"  uid:"+uid+"  name:"+name+"  state:"+state+"  page:"+page+"  rows:"+rows);
    	List<?> list = odao.selectAllOrder(oid, uid, name, state, page, rows);
    	System.out.println("list:"+list);
    	int total = odao.count(oid, uid, name, state);
    	System.out.println("total:"+total);
    	HashMap<String, Object> data = new HashMap<>();
    	data.put("rows", list);
    	data.put("total", total);
		return data;
	}
}
