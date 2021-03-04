package com.yc.flower.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
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
	public Result login1(@Valid @RequestBody Administrator admin, Errors errors, HttpSession session) {
		try {
			if(errors.hasFieldErrors("aname") || errors.hasFieldErrors("apwd")) {
				return Result.failure("字段验证错误", errors.getAllErrors());
			}
			Administrator a  = abiz.login(admin);
			session.setAttribute("loginedUser", a);
			return Result.success("登录成功", a);
		} catch (BizException e) {
			e.printStackTrace();
			return Result.failure("登录失败", e.getMessage());
		}
	}
	
//	@RequestMapping("getLoginedUser1")
//	public Administrator getLoginedUser1(String aname,HttpSession session) {
//		Administrator a = (Administrator) session.getAttribute("loginedUser");
//		return a;
//	}
}
