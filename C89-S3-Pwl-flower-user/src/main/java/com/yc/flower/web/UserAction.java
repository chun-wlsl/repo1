package com.yc.flower.web;



import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.annotation.Resource;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yc.flower.bean.Flower;
import com.yc.flower.bean.Result;
import com.yc.flower.bean.User;
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
			System.out.print("======"+user);
			User dbuser = ubiz.login(user);
			// 登录成功之后，将用户对象发送给调用中
			session.setAttribute("loginedUser", dbuser);
			return Result.success("登录成功", dbuser);
		} catch (BizException e) {
			e.printStackTrace();
			return Result.failure(e.getMessage(), null);
		}
	}
	
	
	//@RequestMapping("getLoginedUser")
	//public User getLoginedUser(HttpSession session) {
	//	User user =(User) session.getAttribute("loginedUser");
	//	return user;
	//}
	
	
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
			return new Result(1,"修改成功！");
		}catch (BizException e) {
			e.printStackTrace();
			return new Result(0,e.getMessage());
		}
	}
	

	//给后台使用，查找所有的
   	@RequestMapping("queryAllUser")
	public Map<String, Object> queryAllUser(Integer uid, String name, String sex, String page, String rows){
   		System.out.println("uid:"+uid+"  name:"+name+"  sex:"+sex+"  page:"+page+"  rows:"+rows);
    	List<?> list = udao.queryAllUser(uid, name, sex, page, rows);
    	System.out.println("list:"+list);
    	int total = udao.count(uid, name, sex);
    	System.out.println("total:"+total);
    	HashMap<String, Object> data = new HashMap<>();
    	data.put("rows", list);
    	data.put("total", total);
		return data;
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
			return new Result(1,"用户保存成功!");
		} catch (BizException e) {
			e.printStackTrace();
			return new Result(0,e.getMessage());
		}
		
	}

}
