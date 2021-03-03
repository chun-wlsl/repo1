package com.yc.flower.web;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yc.flower.bean.Result;
import com.yc.flower.bean.User;
import com.yc.flower.biz.BizException;
import com.yc.flower.util.VerifyCodeUtils;
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
	
	@RequestMapping("getLoginedUser")
	public Result getLoginedUser(HttpSession session) {
		System.out.println(session.getAttribute("loginedUser"));
		return Result.success("会话中的用户对象", session.getAttribute("loginedUser"));
	}
	
	@RequestMapping("reg")
   	public Result reg(User user, String code, HttpSession session) throws BizException {
		System.out.println("==="+user);
		String svcode=(String) session.getAttribute("code");
		if (!code.equalsIgnoreCase(svcode)) {
			throw new BizException("验证码错误");
		}
    	Result ret = iua.reg(user);
		return ret;
   	}
	
	@RequestMapping("verifyCode.s")
	public String getVerifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		String code = VerifyCodeUtils.outputImage(response);
		session.setAttribute("code", code);
		return code;
	}
	
	@RequestMapping("out.s")
	public Result logout() {
		Result ret = iua.logout();
		return ret;
	}
}
