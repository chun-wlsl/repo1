package com.yc.flower.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yc.flower.bean.Msg;
import com.yc.flower.bean.MsgExample;
import com.yc.flower.bean.MsgExample.Criteria;
import com.yc.flower.bean.Result;
import com.yc.flower.bean.User;
import com.yc.flower.bean.UserExample;
import com.yc.flower.biz.BizException;
import com.yc.flower.biz.MsgBiz;
import com.yc.flower.dao.MsgMapper;
import com.yc.flower.dao.UserMapper;

@RestController
public class MsgAction {

	@Resource
	private MsgMapper mm;
	
	@Resource
	private MsgBiz mbiz;
	
	@RequestMapping("addMsg")
	public Result addMsg(String content, Integer uid, Integer fid) {
		Msg msg = new Msg();
		try {
			msg.setUid(uid);
			msg.setContent(content);
			msg.setFid(fid);
			mbiz.addMsg(msg);
			return Result.success("留言添加成功！", null);
		} catch (BizException e) {
			e.printStackTrace();
			return Result.failure("留言添加失败！", e.getMessage());
		}
	}
	
	@RequestMapping("queryMsg")
	public List<?> queryMsg() {
		System.out.println("============"+mm.queryMsg());
		return mm.queryMsg();
	}
	
	@RequestMapping("queryMsgByfid")
	public List<?> queryMsgByfid(@RequestParam Integer fid) {
		System.out.println("*****"+mm.queryMsg());
		return mm.queryMsgByfid(fid);
	}
}
