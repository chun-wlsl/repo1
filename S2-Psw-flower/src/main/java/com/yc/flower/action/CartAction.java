package com.yc.flower.action;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yc.flower.bean.Result;
import com.yc.flower.bean.User;
import com.yc.flower.dao.CartDao;

@RestController
public class CartAction {

	@Resource
	private CartDao cdao;
	
	/**
	 * 添加购物车
	 * 
	 * @param pid     商品id
	 * @param count   商品数量
	 * @param session 会话对象
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping("addCart")
	public Result addCart(int fid, int count, HttpSession session) throws SQLException {
		// 获取当前的登录的用户
		User user = (User) session.getAttribute("loginedUser");
		System.out.println("fid:"+fid);
		System.out.println("count:"+count);
		// 添加购物车记录, 注意:这里没有判断,是否有添加过商品,请自行移植
		cdao.addCart(user.getUid(), fid, count);
		// 返回结果
		return Result.success("添加购物车成功!");
	}
	
	
	/**
	 * 查询购物车
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(path = "cart.s", params = "op=queryCart")
	public List<?> queryCart(HttpSession session) {
		User user = (User) session.getAttribute("loginedUser");
		return cdao.selectCart(user.getUid());
	}
	
	
	@RequestMapping(path = "cart.s", params = "op=upCart")
	public Result upCart(int fid, int count, HttpSession session) {
		User user = (User) session.getAttribute("loginedUser");
		System.out.println("fid" + fid);
		System.out.println("count" + count);
		cdao.upCart(user.getUid(), fid, count);

		return Result.success("操作成功！");

	}
	
	//清空购物车
	@RequestMapping(path = "cart.s", params = "op=clearCart")
	public void clearCart(HttpSession session) throws SQLException {
		User user = (User) session.getAttribute("loginedUser");
		cdao.clearCart(user.getUid());
	}
	
	
	/**public void deleteCart(int fid,int count,HttpSession session) {
		User user = (User) session.getAttribute("loginedUser");
		cdao.deleteCart(fid);
	}*/
	
	//删除购物车中的某件商品fid
	@RequestMapping(path = "cart.s", params = "op=deleteCart")
	public void deleteCart(int fid,HttpSession session) {
		User user = (User) session.getAttribute("loginedUser");
		System.out.println("删除的商品id"+fid);
		
		cdao.deleteCart(fid);
		
		
		
	}
}
