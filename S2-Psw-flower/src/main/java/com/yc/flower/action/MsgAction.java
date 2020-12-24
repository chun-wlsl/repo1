package com.yc.flower.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yc.flower.bean.Msg;
import com.yc.flower.bean.Result;
import com.yc.flower.bean.User;
import com.yc.flower.biz.BizException;
import com.yc.flower.biz.MsgBiz;
import com.yc.flower.dao.MsgDao;

@RestController
public class MsgAction {

	@Resource
	private MsgDao mdao;
	
	@Resource
	private MsgBiz mbiz;
	
	@RequestMapping("addMsg")
	public Result addMsg(String content, HttpSession session) {
		User user =  (User) session.getAttribute("loginedUser");
		Msg msg = new Msg();
		try {
			msg.setUid(user.getUid());
			msg.setContent(content);
			mbiz.addMsg(msg);
			return new Result(1, "留言添加成功！");
		} catch (BizException e) {
			e.printStackTrace();
			return new Result(0, e.getMessage());
		}
	}
	
	@RequestMapping("queryMsg")
	public List<?> queryMsg() {
		return mdao.queryMsg();
	}
}
