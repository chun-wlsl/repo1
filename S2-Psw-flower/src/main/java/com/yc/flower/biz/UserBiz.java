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
	
	//登录
	public User login(String name, String pwd, String code, HttpSession session) throws BizException {
		Utils.checkNull(name,"请输入用户名");
		Utils.checkNull(pwd, "请输入密码");
		
		User user=new User();
		user=udao.selectByName(name);
		
		if(user==null) {
			throw new BizException("请检查用户名是否正确");
		}
		
		if (!user.getPwd().equals(pwd)) {
			throw new BizException("密码错误");
		}
		
		String vcode=(String) session.getAttribute("code");
		if (!code.equalsIgnoreCase(vcode)) {
			throw new BizException("验证码错误");
		}
		return user;
	}

}
