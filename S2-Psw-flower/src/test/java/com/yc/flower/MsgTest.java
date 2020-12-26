package com.yc.flower;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.yc.flower.bean.Msg;
import com.yc.flower.biz.BizException;
import com.yc.flower.biz.MsgBiz;

@SpringBootTest
public class MsgTest {
	
	@Resource
	private MsgBiz mbiz;
	
	@Test
	public void test1() {
		Msg msg = new Msg();
		try {
			msg.setUid(1);
			msg.setContent("太丑了");
			mbiz.addMsg(msg);
			System.out.println(msg.toString());
		} catch (BizException e) {
			e.printStackTrace();
		}
		
	}
}
