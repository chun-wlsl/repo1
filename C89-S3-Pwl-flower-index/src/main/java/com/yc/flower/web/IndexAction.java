package com.yc.flower.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yc.flower.bean.Administrator;
import com.yc.flower.bean.Category;
import com.yc.flower.bean.Flower;
import com.yc.flower.bean.Result;
import com.yc.flower.bean.User;
import com.yc.flower.biz.BizException;
import com.yc.flower.util.VerifyCodeUtils;
import com.yc.flower.web.remote.IFlowerAction;
import com.yc.flower.web.remote.IUserAction;

@RestController
public class IndexAction {

	// 注入远程调用接口
	@Resource
	private IFlowerAction ifa;
	
	@Resource
	private IUserAction iua;
	
	@RequestMapping("addMsg")
	public Result addMsg(String content, Integer uid, Integer fid) {
		Result ret = ifa.addMsg(content, uid, fid);
		return ret;
	}
	
	@RequestMapping("queryMsg")
	public List<?> queryMsg() {
		return ifa.queryMsg();
	}
	
	@RequestMapping("queryMsgByfid")
	public List<?> queryMsgByfid(Integer fid) {
		return ifa.queryMsgByfid(fid);
	}
	
	@RequestMapping("login.s")
	public Result login(User user, HttpSession session) {
		Result ret = iua.login(user);
		if (ret.getCode() == 1) {
			session.setAttribute("loginedUser", ret.getData());
		}
		return ret;
	}
	
	@RequestMapping("getLoginedUser")
	public Result getLoginedUser(HttpSession session) {
		System.out.println(session.getAttribute("loginedUser"));
		return Result.success("会话中的用户对象", session.getAttribute("loginedUser"));
	}
	
	@RequestMapping("reg")
   	public Result reg(User user, String code, HttpSession session) throws BizException {
		System.out.println("==="+user);
		String svcode=(String) session.getAttribute("code");
		if (!code.equalsIgnoreCase(svcode)) {
			throw new BizException("验证码错误");
		}
    	Result ret = iua.reg(user);
		return ret;
   	}
	
	@RequestMapping("verifyCode.s")
	public String getVerifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		String code = VerifyCodeUtils.outputImage(response);
		session.setAttribute("code", code);
		return code;
	}
	
	@RequestMapping("out.s")
	public Result logout(HttpSession session) {
		Object loginedUser = session.getAttribute("loginedUser");
		System.out.println("loginedUser:" + loginedUser);
		if(loginedUser == null) {
			return Result.failure("你还未登录", null);
		}
		session.removeAttribute("loginedUser");
		return Result.success("成功退出", null);
	}
	
//----------------------------------------以下方法还未验证------------------------------
	@RequestMapping("queryCategory")
	public List<Category> queryCategory() {
		return ifa.queryCategory();
	}
	
	@RequestMapping("queryByCid")
	public Category queryByCid(Integer cid) {
		return ifa.queryByCid(cid);
	}
	
	@RequestMapping("insertCategory")
	public Result insertCategory(Category c) {
		Result ret = ifa.insertCategory(c);
		return ret;
	}
	
	@RequestMapping("queryHot")
	public List<Flower> queryHot() {
		return ifa.queryHot();
	}
	
	@RequestMapping("queryFlowerByCid")
	public List<Flower> queryFlowerByCid(int cid) {
		return ifa.queryHot();
	}
	
	@RequestMapping("queryFlowerById")
	public Flower queryFlowerById(int fid) {
		return ifa.queryFlowerById(fid);
	}
	
	@RequestMapping("queryProduct")
	public List<Flower> queryProduct() {
		return ifa.queryProduct();
	}
	
	@RequestMapping("queryDiscount")
	public Flower queryDiscount() {
		return ifa.queryDiscount();
	}
	
	@RequestMapping("create")
	public Result create(Flower f) {
		Result ret = ifa.create(f);
		return ret;
	}
	
	@RequestMapping("queryNewProduct")
	public List<Flower> queryNewProduct() {
		return ifa.queryNewProduct();
	}
	
	@RequestMapping("addCart")
	public Result addCartdetail(int fid, int count) {
		Result ret = ifa.addCartdetail(fid, count);
		return ret;
	}
	
	@RequestMapping(path = "cart.s", params = "op=queryCart")
	public List<?> queryCart() {
		return ifa.queryCart();
	}
	
	@RequestMapping(path = "cart.s", params = "op=upCart")
	public Result upCart(int fid, int count) {
		Result ret = ifa.upCart(fid, count);
		return ret;
	}

	@RequestMapping(path = "cart.s", params = "op=clearCart")
	public Result clearCart() {
		Result ret = ifa.clearCart();
		return ret;
	}
	
	@RequestMapping(path = "cart.s", params = "op=deleteCart")
	public Result deleteCart(int fid) {
		Result ret = ifa.deleteCart(fid);
		return ret;
	}
	
	@RequestMapping("order.s")
	public Result pay(Double total) {
		Result ret = ifa.pay(total);
		return ret;
	}
	
	@RequestMapping("queryOrders")
	public List<Map<String, Object>> queryOrders() {
		return ifa.queryOrders();
	}
	
	@RequestMapping("mksGetPro")
	public Result mksGetPro(int id) {
		Result ret = ifa.mksGetPro(id);
		return ret;
	}
	
	@RequestMapping("queryOrdersByOid")
	public List<?> queryOrdersByOid(Integer oid) {
		return ifa.queryOrdersByOid(oid);
	}
	
	@RequestMapping("order1.s")
	public Result pay1(Integer oid, String addr, String phone, String name, Double total) {
		Result ret = ifa.pay1(oid, addr, phone, name, total);
		return ret;
	}
	
	@RequestMapping("topay.s")
	public Result topay(Integer oid) {
		Result ret = ifa.topay(oid);
		return ret;
	}
	
	@PostMapping(path = "orders.s", params = "op=updateState")
	public Result updateState1(Integer oid) {
		Result ret = ifa.updateState1(oid);
		return ret;
	}
	
	@RequestMapping("login1.s")
	public Result login1(Administrator admin, HttpSession session) {
		Result ret = iua.login1(admin);
		if (ret.getCode() == 1) {
			session.setAttribute("loginedUser", ret.getData());
		}
		return ret;
	}
}
