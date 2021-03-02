package com.yc.flower.web.remote;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.yc.flower.bean.Result;

@FeignClient("flower-flowers")
public interface IMsgAction {

	@PostMapping("addMsg")
	Result addMsg(String content, Integer uid, Integer fid);
	
	@GetMapping("queryMsg")
	List<?> queryMsg();
	
	@GetMapping("queryMsgByfid")
	List<?> queryMsgByfid(Integer fid);
	
}
