package com.yc.flower.action;



import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.yc.flower.dao.UserDao;


@RestController
public class OrderAction {

	@Resource
	private OrderBiz obiz;
	
	@Resource
	private OrderDao odao;
	
	@Resource
    private UserDao udao;
	
	//添加订单成功(结算，shoppingcart.html中的Order方法)
	@RequestMapping("order.s")
	public Result pay(Double total, HttpSession session)  {
		try {
			User user = (User) session.getAttribute("loginedUser");
			Order order = new Order();
			order.setUid(user.getUid());
			order.setTotal(total);
			order.setState(0);
			System.out.println("下订单："+order.toString());
			obiz.insertOrder1(order);
			return Result.success("下单成功!");
		} catch (BizException e) {
			e.printStackTrace();
			return Result.failure(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
			return Result.failure(e.getMessage());
		}
	}
	

	
	/*
	 * @RequestMapping("queryAll")
	public Map<String, Object> queryAll() throws SQLException {
		Map<String,Object> data = new HashMap<>();
		data.put("clist",cdao.queryCategory());
		data.put("cslist",csdao.queryCategorySecond());
		return data;
	}
	 */
	
	
	
	@RequestMapping("queryOrders")
	//查询用户uid的订单（所有）
	public List<Map<String,Object>> queryOrders( HttpSession session) {
		//获取用户uid
		User user = (User) session.getAttribute("loginedUser");
		int uid = udao.queryId(user) ;
		
		Map<String, Object> map;
		//获取用户所有的订单信息
		List<Map<String, Object>> list=	odao.selectOrders(uid);
		
		List<Map<String, Object>> orderitem;
		List<Map<String, Object>> res=new ArrayList<Map<String,Object>>();
		
		for(Map<String,Object> o:list) {
			int oid =(int) o.get("oid");
			
			orderitem  = odao.queryItem(oid);
			map=new HashMap<String,Object>();
			map.put("order",o);
			map.put("orderItem",orderitem);
			res.add(map);
			
		}
		return res;
		
	}
	
	
	//确认收货
	//确认收货
	@RequestMapping("mksGetPro")
		public Result mksGetPro(int id){
            System.out.println("收货的订单编号："+id);
			int result = odao.mksGetPro(id);
			if(result==0) {
				return Result.failure("收货失败!");
			}else {
				return Result.success("收货成功!");
			}
			
		}
	
	
	
	
	
	
//		
//		
//		return null;
//	}
//	
	
	/*
	 * public Map<String, Object> queryAll() throws SQLException {
		Map<String,Object> data = new HashMap<>();
		data.put("clist",cdao.queryCategory());
		data.put("cslist",csdao.queryCategorySecond());
		return data;
	}
	 */
	

	
	
//	
//	//支付成功
//	@RequestMapping("updateState")
//	public Result  updateState(int oid,String addr,String phone,String name) throws BizException {
//	
//		//odao.updateState(1);
//		Order order =new Order();
//		order.setOid(oid);
//		order.setName(name);
//		order.setAddr(addr);
//		order.setPhone(phone);
//		System.out.println("订单编号"+oid+"收货人："+name+"\n收货地址："+addr+"\n收货电话"+phone);
//		System.out.println(order.toString());
//		obiz.pay(order);
//		return Result.success("支付成功!");
//	}
//	
	
	//后台
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

   	@RequestMapping("queryOrdersByOid")
	//查询oid的订单详情（所有）
	public List<?> queryOrdersByOid(Integer oid) {
		List<?> orderitem = odao.queryItembyOid(oid);
		return orderitem;
	}
	
   	//更新订单成功(checkout.html中的check方法）
  	@RequestMapping("order1.s")
  	public Result pay1(Integer oid,String addr,String phone,String name)  {
  		try {
  			Order order = new Order();
  			order.setOid(oid);
  			order.setName(name);
  			order.setPhone(phone);
  			order.setAddr(addr);
  			System.out.println("订单"+order.toString());
  			obiz.insertOrder2(order);
  			return Result.success("支付成功!");
  		} catch (BizException e) {
  			e.printStackTrace();
  			return Result.failure(e.getMessage());
  		} catch (SQLException e) {
  			e.printStackTrace();
  			return Result.failure(e.getMessage());
  		}
  	}
   	
  	@RequestMapping("topay.s")
  	public Result topay(Integer oid)  {
  		int state = 1;
  		int i = odao.updateState(state , oid) ;
  		if( i > 0) {
  			return Result.success("下单成功!");
  		}else {
  			return Result.failure("下单失败!");
  		}
  	}
}
