package com.yc.flower.web;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yc.flower.bean.Result;
import com.yc.flower.bean.User;
import com.yc.flower.biz.BizException;
import com.yc.flower.biz.FlowerBiz;
import com.yc.flower.dao.CartDao;

@RestController
public class CartAction {

	@Resource
	private CartDao cdao;
	
	@Resource
	private FlowerBiz fbiz;
	
	/**
	 * 添加购物车
	 * producctdetail.html的添加购物车
	 * @param pid     商品id
	 * @param count   商品数量
	 * @param session 会话对象
	 * @return
	 * @throws SQLException
	 * @throws BizException 
	 */
	@RequestMapping("addCart")
	public Result addCartdetail(int fid, int count, HttpSession session) {
		// 获取当前的登录的用户
		User user = (User) session.getAttribute("loginedUser");
		System.out.println("fid:"+fid);
		System.out.println("count:"+count);
		// 添加购物车记录, 注意:这里没有判断,是否有添加过商品,请自行移植
		try {
			fbiz.addCart(user.getUid(), fid, count);
			return Result.success("添加购物车成功!");
		} catch (SQLException e) {
			e.printStackTrace();
			return Result.failure("系统错误，请联系管理员！");
		} catch (BizException e) {
			e.printStackTrace();
			return Result.failure(e.getMessage());
		}
		// 返回结果
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
	
	//更新购物车
	@RequestMapping(path = "cart.s", params = "op=upCart")
	public Result upCart(int fid, int count, HttpSession session) {
		User user = (User) session.getAttribute("loginedUser");
		System.out.println("fid" + fid);
		System.out.println("count" + count);
		int i = cdao.upCart(user.getUid(), fid, count);
		if(i > 0) {
			return Result.success("更新成功！");
		}else {
			return Result.failure("更新失败！");
		}
	}
	
	//清空购物车
	@RequestMapping(path = "cart.s", params = "op=clearCart")
	public Result clearCart(HttpSession session) throws SQLException {
		User user = (User) session.getAttribute("loginedUser");
		int i = cdao.clearCart(user.getUid());
		if(i > 0) {
			return Result.success("清空购物车成功！");
		}else {
			return Result.failure("清空购物车失败！");
		}
	}
	
	
	/**public void deleteCart(int fid,int count,HttpSession session) {
		User user = (User) session.getAttribute("loginedUser");
		cdao.deleteCart(fid);
	}*/
	
	//删除购物车中的某件商品fid
	@RequestMapping(path = "cart.s", params = "op=deleteCart")
	public Result deleteCart(int fid,HttpSession session) {
		User user = (User) session.getAttribute("loginedUser");
		System.out.println("删除的商品id"+fid);
		int i = cdao.deleteCart(fid);
		if(i > 0) {
			return Result.success("删除鲜花成功！");
		}else {
			return Result.failure("删除鲜花失败！");
		}
	}
}
