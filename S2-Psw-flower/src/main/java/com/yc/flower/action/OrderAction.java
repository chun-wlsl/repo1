package com.yc.flower.action;

import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
