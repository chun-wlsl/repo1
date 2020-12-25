package com.yc.flower.biz;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.flower.bean.Order;
import com.yc.flower.dao.CartDao;
import com.yc.flower.dao.OrderDao;
import com.yc.flower.util.Utils;



@Service
public class OrderBiz {

	@Resource
	private CartDao cdao;
	
	@Resource
	private OrderDao odao;
	
	@Transactional
	public void pay(Order order) throws BizException, SQLException{

		// 验证输入值
		Utils.checkNull(order.getAddr(), "请填写收货地址");
		Utils.checkNull(order.getPhone(), "请填写收货人电话");
		Utils.checkNull(order.getName(), "请填写收货人姓名");
		
		// 计算总金额
		Double total = cdao.selectTotalByUid(order.getUid());
		order.setTotal(total);
		order.setState(0);
		
		// 写入数据库
		// 订单主表  orders
		int oid = odao.insertOrder(order);
		// 订单明细 orderitem
		order.setOid(oid);
		odao.insertItems(order);
		// 清空购物车 cart ==> uid
		cdao.clearCart(order.getUid());

	}
	
}
