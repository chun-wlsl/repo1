package com.yc.flower.biz;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.flower.bean.Msg;
import com.yc.flower.dao.MsgMapper;
import com.yc.flower.util.Utils;


@Service
public class MsgBiz{
	
	@Resource
	private MsgMapper mm;
	
	@Transactional
	public void addMsg(Msg m) throws BizException{
		// 验证输入
		Utils.checkNull(m.getContent(), "留言内容不能为空");
		Utils.checkNull(m.getUid(), "用户ID不能为空");
		Utils.checkNull(m.getFid(), "鲜花ID不能为空");
		// 添加到数据库
		mm.insertSelective(m);
	}
	
}
