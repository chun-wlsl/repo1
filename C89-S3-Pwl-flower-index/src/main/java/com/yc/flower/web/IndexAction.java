package com.yc.flower.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yc.flower.bean.Result;
import com.yc.flower.bean.User;
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
}
