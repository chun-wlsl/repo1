package com.yc.flower.action;



import java.util.List;

import javax.annotation.Resource;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yc.flower.bean.Result;
import com.yc.flower.bean.User;
import com.yc.flower.biz.VcodeBiz;
import com.yc.flower.biz.BizException;
import com.yc.flower.biz.UserBiz;
import com.yc.flower.dao.UserDao;

@RestController
public class UserAction {

	@Resource
	private UserDao udao;
	
	@Resource
	private UserBiz ubiz;
	
	@Resource 
	private VcodeBiz bbiz; 
	
	
	@RequestMapping("login.s")
	public Result login(String username,String password,HttpSession session) {
		User user;
		try {
			user=ubiz.login(username, password,session);
			session.setAttribute("loginedUser", user);
			return new Result(1,"登录成功");
		} catch (BizException e) {
			e.printStackTrace();
			return new Result(0,e.getMessage());
		}
	}
	
	
	@RequestMapping("getLoginedUser")
	public User getLoginedUser(String username,HttpSession session) {
		User user =(User) session.getAttribute("loginedUser");
		return user;
	}
	
	
	@RequestMapping("reg")
	public Result reg(User user,String code,HttpSession session) throws Exception {
		try {
			String svcode=(String) session.getAttribute("code");
			if (!code.equalsIgnoreCase(svcode)) {
			throw new BizException("验证码错误");
			}
			ubiz.register(user);
			return new Result(1,"注册成功");
		} catch (BizException e) {
			e.printStackTrace();
			return new Result(0,e.getMessage());
		}
	}
	
	
	//查询总用户数
	public int queryAll() {
	   List<User> list=udao.selectAllUser();
		return  list.size() ;
	}
	
	
	//验证码
	/**
	 * SpringMVC 接收请求参数的语法
	 * 
	 * HttpSession SpringMVC(会自动注入一个绘画对象)
	 * 
	 * */
	@RequestMapping("sendVcode")
	public String sendVcode(String name,HttpSession session) {
		//根据用户名发送验证短信（业务逻辑，BankBiz.sendVcode）
		String vcode=bbiz.sendVcode(name);
		//将验证码保存到绘画
		session.setAttribute("vcode", vcode);
		//通知浏览器发送成功
		return "验证码邮件发送成功";
	}
	
	@RequestMapping("resetPwd")
	public String resetPwd(String name,String vcode,String password,HttpSession session) {
		return bbiz.resetPwd(name, vcode, password, (String)session.getAttribute("vcode"));
	}
	
}
