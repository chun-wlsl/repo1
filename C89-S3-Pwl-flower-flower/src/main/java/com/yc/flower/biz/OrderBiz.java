package com.yc.flower.biz;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.flower.bean.Order;
import com.yc.flower.dao.CartMapper;
import com.yc.flower.dao.OrderMapper;
import com.yc.flower.dao.OrderitemMapper;
import com.yc.flower.util.Utils;

@Service
public class OrderBiz {

	@Resource
	private CartMapper cm;
	// private CartDao cdao;

	@Resource
	private OrderMapper om;
	// private OrderDao odao;

	@Resource
	private OrderitemMapper oim;
	
	@Transactional
	public void insertOrder(Order order) throws BizException, SQLException {

		// 验证输入值
		Utils.checkNull(order.getAddr(), "请填写收货地址");
		Utils.checkNull(order.getPhone(), "请填写收货人电话");
		Utils.checkNull(order.getName(), "请填写收货人姓名");

		// 计算总金额
		Double total = cm.selectTotalByUid(order.getUid());
		order.setTotal(total);
		order.setState(0);

		// 写入数据库
		// 订单主表 orders
		int oid = om.insertOrder(order);
		// 订单明细 orderitem
		order.setOid(oid);
		oim.insertItems(order.getOid(), order.getUid());
		// 清空购物车 cart ==> uid
		cm.clearCart(order.getUid());

	}

	public void insertOrder1(Order order) throws BizException, SQLException {
		// 写入数据库
		// 订单主表 orders
		int oid = om.insertOrder(order);
		// 订单明细 orderitem
		order.setOid(oid);
		oim.insertItems(order.getOid(), order.getUid());
		// 清空购物车 cart ==> uid
		cm.clearCart(order.getUid());

	}

	// 后台
	public void save(Order o) throws BizException {
		Utils.checkNull(o.getUid(), "用户ID不能为空");
		Utils.checkNull(o.getName(), "收货人姓名不能为空");
		Utils.checkNull(o.getTotal(), "订单总金额不能为空");
		Utils.checkNull(o.getAddr(), "收货地址不能为空");
		Utils.checkNull(o.getPhone(), "收货人电话不能为空");
		Utils.checkNull(o.getState(), "收货人电话不能为空");

		if (o.getOid() == null || o.getOid() == 0) {
			om.insertOrder(o);
		} else {
			om.update(o);
		}
	}

	public void insertOrder2(Order order) throws BizException, SQLException {
		// 验证输入值
		Utils.checkNull(order.getAddr(), "请填写收货地址");
		Utils.checkNull(order.getPhone(), "请填写收货人电话");
		Utils.checkNull(order.getName(), "请填写收货人姓名");
		// 写入数据库
		om.updateByOid(order);

	}

//	//支付成功（改变订单的状态  1 已支付）   
//	public void pay(Order order) throws BizException {
//		Utils.checkNull(order.getAddr(), "请填写收货地址");
//	    Utils.checkNull(order.getPhone(), "请填写收货人电话");
//	    Utils.checkNull(order.getName(), "请填写收货人姓名");
//	     
//	    int i=  odao.checkState(order);
//	    
//	    System.out.println(i);
//	}

}
