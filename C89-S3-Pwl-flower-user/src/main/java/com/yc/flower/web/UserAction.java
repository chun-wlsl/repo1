package com.yc.flower.web;

import javax.annotation.Resource;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yc.flower.bean.Result;
import com.yc.flower.bean.User;
import com.yc.flower.bean.UserExample;
import com.yc.flower.biz.BizException;
import com.yc.flower.biz.UserBiz;
import com.yc.flower.dao.UserMapper;

@RestController
public class UserAction {

	@Resource
	private UserMapper um;
	//private UserDao udao;
	
	@Resource
	private UserBiz ubiz;
	
	//Feign 接口方法参数要加 RequstBody 注解
	@RequestMapping("login.s")
	public Result login(@Valid @RequestBody User user, Errors errors, HttpSession session) {
		try {
			if(errors.hasFieldErrors("name") || errors.hasFieldErrors("pwd")) {
				return Result.failure("字段验证错误", errors.getAllErrors());
			}
			User dbuser = ubiz.login(user);
			// 登录成功之后，将用户对象发送给调用中
			session.setAttribute("loginedUser", dbuser);
			return Result.success("登录成功", dbuser);
		} catch (BizException e) {
			e.printStackTrace();
			return Result.failure("登录失败", e.getMessage());
		}
	}
	
	
	//@RequestMapping("getLoginedUser")
	//public User getLoginedUser(HttpSession session) {
	//	User user =(User) session.getAttribute("loginedUser");
	//	return user;
	//}
	
	
	@RequestMapping("reg")
	public Result reg(@Valid @RequestBody User user, Errors errors) {
		// 判断是否出现验证错误
		System.out.println("==="+user);
		if (errors.hasFieldErrors("phone") || errors.hasFieldErrors("name") || errors.hasFieldErrors("addr")
				|| errors.hasFieldErrors("pwd") || errors.hasFieldErrors("email") || errors.hasFieldErrors("sex")) {
			return Result.failure("字段验证错误！", errors.getAllErrors());
		}
		try {
			ubiz.register(user);
			return Result.success("注册成功", null);
		} catch (BizException e) {
			e.printStackTrace();
			errors.rejectValue("name", "NotOne", e.getMessage());
			return Result.failure("注册成功", e.getMessage());
		}
	}
	
	//查询总用户数
	public int queryAll() {
		UserExample ue = new UserExample();
		//List<User> list=udao.selectAllUser();
		return  (int) um.countByExample(ue) ;
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
		String vcode=ubiz.sendVcode(name);
		//将验证码保存到绘画
		session.setAttribute("vcode", vcode);
		//通知浏览器发送成功
		return "验证码邮件发送成功";
	}
	
	@RequestMapping("resetPwd")
	public String resetPwd(String name,String vcode,String password,HttpSession session) {
		return ubiz.resetPwd(name, vcode, password, (String)session.getAttribute("vcode"));
	}
	
	//path = "cart.s", params = "op=deleteCart"
	@RequestMapping(path="reUser.s",params = "op=reU")
	public Result reUser(HttpSession session,String name,String pwd,String sex,String phone,String addr,String email) throws Exception {
	
		User user =(User) session.getAttribute("loginedUser");
		
		user.setName(name);
		user.setPwd(pwd);
		user.setSex(sex);
		user.setPhone(phone);
		user.setAddr(addr);
		user.setEmail(email);
		System.out.println(user.toString());
		try {
			ubiz.reUser(user);
			return Result.success("修改成功！", null);
		}catch (BizException e) {
			e.printStackTrace();
			return Result.failure("修改失败！",e.getMessage());
		}
	}
   	
   	//给后台使用，flower页面的save
   	@RequestMapping(path="user.s",params="op=save")
	public Result save(Integer uid, String name, String pwd, String sex, String phone, String addr, String email){
   		User user = new User();
   		user.setUid(uid);
		user.setName(name);
		user.setPwd(pwd);
		user.setSex(sex);
		user.setPhone(phone);
		user.setAddr(addr);
		user.setEmail(email);
		try {
			ubiz.save(user);
			return Result.success("用户保存成功!", null);
		} catch (BizException e) {
			e.printStackTrace();
			return Result.failure("用户保存失败!",e.getMessage());
		}
		
	}

}
