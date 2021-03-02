package com.yc.flower.web.remote;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yc.flower.bean.Result;

//远程调用服务接口--flower工程的所有方法（包括Category、Flower、Msg）
@FeignClient("flower-flowers")
public interface IFlowerAction {

	@PostMapping("addMsg")
	Result addMsg(@RequestParam String content, @RequestParam Integer uid, @RequestParam Integer fid);
	
	@GetMapping("queryMsg")
	List<?> queryMsg();
	
	@GetMapping("queryMsgByfid")
	List<?> queryMsgByfid(@RequestParam Integer fid);
}
