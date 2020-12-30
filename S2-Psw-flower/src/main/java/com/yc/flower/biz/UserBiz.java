package com.yc.flower.biz;

import javax.annotation.Resource;


import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.yc.flower.bean.User;
import com.yc.flower.dao.UserDao;
import com.yc.flower.util.Utils;

@Service
public class UserBiz {

	@Resource
	private UserDao udao;
	
	@Resource
	private MailBiz mbiz;
	
	
	//登录
	public User login(String name, String pwd,HttpSession session) throws BizException {
		Utils.checkNull(name,"请输入用户名");
		Utils.checkNull(pwd, "请输入密码");
		
		System.out.println(name);
		
		User user=new User();
		user=udao.selectByName(name);
		
		if(user==null) {
			throw new BizException("请检查用户名是否正确");
		}
		
		if (!user.getPwd().equals(pwd)) {
			throw new BizException("密码错误");
		}
		
		//@SuppressWarnings("null")
		//String svcode = (String) session.getAttribute("vcode");
		//if(!vcode.equalsIgnoreCase(svcode)) {
			//throw new BizException("验证码错误");
		//}
		return user;
	}

	
	//注册
	public void register(User user) throws Exception {
		//字段验证
		Utils.checkNull(user.getName(), "用户名不能为空");
		Utils.checkNull(user.getPwd(), "密码不能为空");
		Utils.checkNull(user.getSex(), "性别不能为空");
		Utils.checkNull(user.getPhone(), "电话号码不能为空");
		Utils.checkNull(user.getAddr(), "居住地址不能为空");
		Utils.checkNull(user.getEmail(),"邮箱地址不能为空");
		//Utils.checkNull(user.getCode(),"验证码不能为空");
		
		
		//同名验证
		User dbUser=udao.selectByName(user.getName());
		if (dbUser !=null) {
			throw new BizException("该用户名已经被注册");
		}
		
		try {
			udao.insert(user);
		} catch (Exception e) {
			throw new BizException("注册失败，请联系管理员",e);
		}
	}

	
	
	//用户更改个人信息
		public void reUser(User user) throws Exception {
			//字段验证
			Utils.checkNull(user.getName(), "用户名不能为空");
			Utils.checkNull(user.getPwd(), "密码不能为空");
			Utils.checkNull(user.getSex(), "性别不能为空");
			Utils.checkNull(user.getPhone(),"电话号码不能为空");
			Utils.checkNull(user.getAddr(), "居住地址不能为空");
			Utils.checkNull(user.getEmail(),"邮箱地址不能为空");
			//Utils.checkNull(user.getCode(),"验证码不能为空");
			
			
			//同名验证
			User dbUser=udao.selectByName(user.getName());
			if (dbUser !=null) {
				throw new BizException("该用户名不能使用");
			}
			
			try {
				udao.updateUser(user);
			} catch (Exception e) {
				throw new BizException("修改失败",e);
			}
		}
	
	
	//验证码
	public String sendVcode(String name) {
		User user =udao.selectByName(name);
		//随机生成验证码
		String vcode=""+System.currentTimeMillis();
		vcode=vcode.substring(vcode.length()-4);
		mbiz.sendSimpleMail(user.getEmail(), "密码重置验证码","请使用"+vcode+"验证码来设置验证码");
		return vcode;
	}


	//给后台使用
	public void save(User u) throws BizException {
		Utils.checkNull(u.getName(), "用户名不能为空");
		Utils.checkNull(u.getPwd(), "密码不能为空");
		Utils.checkNull(u.getSex(), "性别不能为空");
		Utils.checkNull(u.getPhone(), "电话号码不能为空");
		Utils.checkNull(u.getAddr(), "居住地址不能为空");
		Utils.checkNull(u.getEmail(),"邮箱地址不能为空");
		
		if(u.getUid() == null || u.getUid() == 0) {
			udao.insert(u);
		} else {
			udao.update(u);
		}
	}
}
