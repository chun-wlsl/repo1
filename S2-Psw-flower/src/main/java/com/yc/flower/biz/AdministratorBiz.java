package com.yc.flower.biz;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.yc.flower.bean.Administrator;
import com.yc.flower.bean.User;
import com.yc.flower.dao.AdministratorDao;
import com.yc.flower.util.Utils;

public class AdministratorBiz {

	@Resource
	private AdministratorDao adao;
	
	// 登录
	public Administrator login(String name, String pwd, HttpSession session) throws BizException {
		Utils.checkNull(name, "请输入用户名");
		Utils.checkNull(pwd, "请输入密码");

		System.out.println(name);

		Administrator user = new Administrator();
		/*
		 * user = adao.selectByName(name);
		 * 
		 * if (user == null) { throw new BizException("请检查用户名是否正确"); }
		 * 
		 * if (!user.getPwd().equals(pwd)) { throw new BizException("密码错误"); } return
		 * user;
		 */
		return user;
	}
}
