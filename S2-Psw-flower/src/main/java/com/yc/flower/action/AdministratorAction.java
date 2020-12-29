package com.yc.flower.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yc.flower.bean.Administrator;
import com.yc.flower.bean.Result;
import com.yc.flower.biz.AdministratorBiz;
import com.yc.flower.biz.BizException;

@RestController
public class AdministratorAction {

	@Resource
	private AdministratorBiz abiz;
	
	@RequestMapping("login1.s")
	public Result login(String aname,String apwd,HttpSession session) {
		Administrator a;
		try {
			a = abiz.login(aname, apwd);
			session.setAttribute("loginedUser", a);
			return new Result(1,"登录成功");
		} catch (BizException e) {
			e.printStackTrace();
			return new Result(0,e.getMessage());
		}
	}
	
	@RequestMapping("getLoginedUser1")
	public Administrator getLoginedUser(String aname,HttpSession session) {
		Administrator a = (Administrator) session.getAttribute("loginedUser");
		return a;
	}
}
