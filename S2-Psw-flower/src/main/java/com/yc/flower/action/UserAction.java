package com.yc.flower.action;



import java.util.List;

import javax.annotation.Resource;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yc.flower.bean.Result;
import com.yc.flower.bean.User;
import com.yc.flower.biz.BizException;
import com.yc.flower.biz.UserBiz;
import com.yc.flower.dao.UserDao;

@RestController
public class UserAction {

	@Resource
	private UserDao udao;
	
	@Resource
	private UserBiz ubiz;
	
	
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
	
}
